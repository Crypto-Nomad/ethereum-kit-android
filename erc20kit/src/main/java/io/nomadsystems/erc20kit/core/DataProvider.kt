package io.nomadsystems.erc20kit.core

import io.nomadsystems.erc20kit.contract.BalanceOfMethod
import io.nomadsystems.ethereumkit.core.EthereumKit
import io.nomadsystems.ethereumkit.models.Address
import io.nomadsystems.ethereumkit.spv.core.toBigInteger
import io.reactivex.Single
import java.math.BigInteger

class DataProvider(
        private val ethereumKit: EthereumKit
) : IDataProvider {

    override fun getBalance(contractAddress: Address, address: Address): Single<BigInteger> {
        return ethereumKit.call(contractAddress, BalanceOfMethod(address).encodedABI())
                .map { it.toBigInteger() }
    }

}
