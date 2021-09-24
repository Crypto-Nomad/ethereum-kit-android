package io.nomadsystems.erc20kit.contract

import io.nomadsystems.ethereumkit.contracts.ContractMethod
import io.nomadsystems.ethereumkit.models.Address

class BalanceOfMethod(val owner: Address) : ContractMethod() {

    override val methodSignature = "balanceOf(address)"
    override fun getArguments() = listOf(owner)

}
