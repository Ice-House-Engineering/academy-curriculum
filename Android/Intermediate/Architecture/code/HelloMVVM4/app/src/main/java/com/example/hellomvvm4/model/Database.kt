package com.example.hellomvvm4.model


class Database {

    companion object {

        private var cryptocurrencies = "BITCOIN"

        fun getCryptocurrencies() = cryptocurrencies

        fun setCryptocurrencies(newCryptocurrencies : String) {
            cryptocurrencies = newCryptocurrencies
        }
    }
}