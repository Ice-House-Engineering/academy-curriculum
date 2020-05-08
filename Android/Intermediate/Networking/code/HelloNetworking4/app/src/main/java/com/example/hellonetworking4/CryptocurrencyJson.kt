package com.example.hellonetworking4


class Cryptocurrency(
    val name: String,
    val price: Int
) {
    override fun toString(): String {
        return "Cryptocurrency [name: ${this.name}, price: ${this.price}]"
    }
}

class CryptocurrencyJson(
    val release_date: String,
    val cryptocurrencies: ArrayList<Cryptocurrency>
) {
    override fun toString(): String {
        return "CryptocurrencyJson [release_date: ${this.release_date}, cryptocurrencies: ${this.cryptocurrencies}]"
    }
}