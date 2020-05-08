package com.example.hellomvvm1.model


class Database {

    companion object {

        private var cryptocurrencies = "BITCOIN"

        fun getCryptocurrencies() = cryptocurrencies

        fun setCryptocurrencies(newCryptocurrencies : String) {
            cryptocurrencies = newCryptocurrencies
        }
    }
}