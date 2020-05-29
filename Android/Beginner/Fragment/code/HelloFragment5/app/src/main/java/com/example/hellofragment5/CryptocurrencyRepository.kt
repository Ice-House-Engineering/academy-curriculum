package com.example.hellofragment5


object CryptocurrencyRepository {
    private val cryptocurrencies = listOf(
        CryptocurrencyModel(
            id = 1,
            name = "Bitcoin",
            url = "https://bitcoin.org",
            createdOn = 2009
        ),
        CryptocurrencyModel(
            id = 2,
            name = "Ethereum",
            url = "https://ethereum.org",
            createdOn = 2014
        ),
        CryptocurrencyModel(
            id = 3,
            name = "Bitcoin Cash",
            url = "https://bitcoin.com",
            createdOn = 2015
        ),
        CryptocurrencyModel(
            id = 4,
            name = "Litecoin",
            url = "https://litecoin.org",
            createdOn = 2011
        )
    ).associateBy { it.id }

    fun getCryptocurrencies() : List<CryptocurrencyModel> = cryptocurrencies.values.toList()

    fun findCryptocurrencyById(id: Int) = cryptocurrencies[id]
}