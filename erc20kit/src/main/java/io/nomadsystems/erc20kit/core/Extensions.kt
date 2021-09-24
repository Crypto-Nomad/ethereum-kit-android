package io.nomadsystems.erc20kit.core

import io.nomadsystems.erc20kit.decorations.ApproveEventDecoration
import io.nomadsystems.erc20kit.decorations.TransferEventDecoration
import io.nomadsystems.ethereumkit.core.hexStringToByteArrayOrNull
import io.nomadsystems.ethereumkit.core.toRawHexString
import io.nomadsystems.ethereumkit.decorations.ContractEventDecoration
import io.nomadsystems.ethereumkit.models.Address
import io.nomadsystems.ethereumkit.models.TransactionLog
import java.math.BigInteger

fun TransactionLog.getErc20Event(): ContractEventDecoration? {
    return try {
        if (topics.size != 3) {
            return null
        }

        val signature = topics[0].hexStringToByteArrayOrNull()

        val firstParam = Address(topics[1])
        val secondParam = Address(topics[2])

        when {
            signature.contentEquals(TransferEventDecoration.signature) ->
                TransferEventDecoration(address, firstParam, secondParam, BigInteger(data.toRawHexString(), 16))
            signature.contentEquals(ApproveEventDecoration.signature) ->
                ApproveEventDecoration(address, firstParam, secondParam, BigInteger(data.toRawHexString(), 16))
            else ->
                null
        }
    } catch (error: Throwable) {
        error.printStackTrace()
        null
    }
}
