package com.example.hellomvvm5.model


class Database {

    companion object {

        private val cryptocurrencies = mutableMapOf(
            0 to CryptocurrencyModel(
                name = "BITCOIN",
                title = "https://bitcoin.org"
            ),
            1 to CryptocurrencyModel(
                name = "ETHEREUM",
                title = "https://ethereum.org"
            ),
            2 to CryptocurrencyModel(
                name = "BITCOIN CASH",
                title = "https://bitcoin.com"
            ),
            3 to CryptocurrencyModel(
                name = "LITECOIN",
                title = "https://litecoin.org"
            )
        )

        fun getCryptocurrencies() = cryptocurrencies

        fun addToCryptocurrencies(newCryptocurrency : CryptocurrencyModel) {
            cryptocurrencies[cryptocurrencies.count()] = newCryptocurrency
        }

        fun findCryptocurrency(name : String) : CryptocurrencyModel? {
            var cryptocurrencyModel : CryptocurrencyModel? = null
            for ((_, value) in cryptocurrencies) {
                if (value.name==name) {
                    cryptocurrencyModel = value
                    break
                }
            }
            return cryptocurrencyModel
        }
    }
}