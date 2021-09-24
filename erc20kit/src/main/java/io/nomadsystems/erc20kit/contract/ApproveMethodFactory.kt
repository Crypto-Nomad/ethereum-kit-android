package io.nomadsystems.erc20kit.contract

import io.nomadsystems.ethereumkit.contracts.ContractMethodFactory
import io.nomadsystems.ethereumkit.contracts.ContractMethodHelper
import io.nomadsystems.ethereumkit.models.Address
import io.nomadsystems.ethereumkit.spv.core.toBigInteger

object ApproveMethodFactory : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(ApproveMethod.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ApproveMethod {
        val address = Address(inputArguments.copyOfRange(12, 32))
        val value = inputArguments.copyOfRange(32, 64).toBigInteger()

        return ApproveMethod(address, value)
    }

}
