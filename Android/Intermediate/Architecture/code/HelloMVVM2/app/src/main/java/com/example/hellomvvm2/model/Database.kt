package com.example.hellomvvm2.model


class Database {

    companion object {

        private var cryptocurrencies = "BITCOIN"

        fun getCryptocurrencies() = cryptocurrencies

        fun setCryptocurrencies(newCryptocurrencies : String) {
            cryptocurrencies = newCryptocurrencies
        }
    }
}