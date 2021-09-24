package io.nomadsystems.erc20kit.contract

import io.nomadsystems.ethereumkit.contracts.ContractMethod
import io.nomadsystems.ethereumkit.models.Address

class AllowanceMethod(val owner: Address, val spender: Address) : ContractMethod() {

    override val methodSignature = "allowance(address,address)"
    override fun getArguments() = listOf(owner, spender)

}
