package io.nomadsystems.erc20kit.contract

import io.nomadsystems.ethereumkit.contracts.ContractMethodFactory
import io.nomadsystems.ethereumkit.contracts.ContractMethodHelper
import io.nomadsystems.ethereumkit.models.Address
import io.nomadsystems.ethereumkit.spv.core.toBigInteger

object TransferMethodFactory : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(TransferMethod.methodSignature)

    override fun createMethod(inputArguments: ByteArray): TransferMethod {
        val address = Address(inputArguments.copyOfRange(12, 32))
        val value = inputArguments.copyOfRange(32, 64).toBigInteger()

        return TransferMethod(address, value)
    }

}
