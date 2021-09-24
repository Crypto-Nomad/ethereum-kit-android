package io.nomadsystems.erc20kit.contract

import io.nomadsystems.ethereumkit.contracts.ContractMethod
import io.nomadsystems.ethereumkit.models.Address
import java.math.BigInteger

class ApproveMethod(val spender: Address, val value: BigInteger) : ContractMethod() {

    override val methodSignature = ApproveMethod.methodSignature
    override fun getArguments() = listOf(spender, value)

    companion object {
        const val methodSignature = "approve(address,uint256)"
    }

}
