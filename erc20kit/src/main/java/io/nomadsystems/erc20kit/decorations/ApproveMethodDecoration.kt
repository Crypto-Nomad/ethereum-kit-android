package io.nomadsystems.erc20kit.decorations

import io.nomadsystems.ethereumkit.decorations.ContractMethodDecoration
import io.nomadsystems.ethereumkit.models.Address
import io.nomadsystems.ethereumkit.models.TransactionTag
import java.math.BigInteger

class ApproveMethodDecoration(val spender: Address, val value: BigInteger) : ContractMethodDecoration() {

    override fun tags(fromAddress: Address, toAddress: Address, userAddress: Address): List<String> {
        return listOf(toAddress.hex, TransactionTag.EIP20_APPROVE)
    }
}
