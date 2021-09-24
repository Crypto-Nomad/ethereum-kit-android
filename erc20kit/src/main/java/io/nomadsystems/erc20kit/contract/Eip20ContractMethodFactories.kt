package io.nomadsystems.erc20kit.contract

import io.nomadsystems.ethereumkit.contracts.ContractMethodFactories

object Eip20ContractMethodFactories : ContractMethodFactories() {

    init {
        registerMethodFactories(listOf(TransferMethodFactory, ApproveMethodFactory))
    }

}
