package io.nomadsystems.erc20kit.core

import io.nomadsystems.erc20kit.contract.ApproveMethod
import io.nomadsystems.erc20kit.contract.Eip20ContractMethodFactories
import io.nomadsystems.erc20kit.contract.TransferMethod
import io.nomadsystems.erc20kit.decorations.ApproveEventDecoration
import io.nomadsystems.erc20kit.decorations.ApproveMethodDecoration
import io.nomadsystems.erc20kit.decorations.TransferEventDecoration
import io.nomadsystems.erc20kit.decorations.TransferMethodDecoration
import io.nomadsystems.ethereumkit.core.IDecorator
import io.nomadsystems.ethereumkit.decorations.ContractEventDecoration
import io.nomadsystems.ethereumkit.decorations.ContractMethodDecoration
import io.nomadsystems.ethereumkit.models.Address
import io.nomadsystems.ethereumkit.models.FullTransaction
import io.nomadsystems.ethereumkit.models.TransactionData
import io.nomadsystems.ethereumkit.models.TransactionLog


class Eip20TransactionDecorator(
        private val userAddress: Address,
        private val contractMethodFactories: Eip20ContractMethodFactories
) : IDecorator {

    override fun decorate(transactionData: TransactionData, fullTransaction: FullTransaction?): ContractMethodDecoration? =
            when (val contractMethod = contractMethodFactories.createMethodFromInput(transactionData.input)) {
                is TransferMethod -> TransferMethodDecoration(contractMethod.to, contractMethod.value)
                is ApproveMethod -> ApproveMethodDecoration(contractMethod.spender, contractMethod.value)
                else -> null
            }

    override fun decorate(logs: List<TransactionLog>): List<ContractEventDecoration> {
        return logs.mapNotNull { log ->

            val event = log.getErc20Event() ?: return@mapNotNull null

            when (event) {
                is TransferEventDecoration -> {
                    if (event.from == userAddress || event.to == userAddress) {
                        log.relevant = true
                        return@mapNotNull event
                    }
                }
                is ApproveEventDecoration -> {
                    if (event.owner == userAddress || event.spender == userAddress) {
                        log.relevant = true
                        return@mapNotNull event
                    }
                }
            }

            return@mapNotNull null
        }
    }
}
