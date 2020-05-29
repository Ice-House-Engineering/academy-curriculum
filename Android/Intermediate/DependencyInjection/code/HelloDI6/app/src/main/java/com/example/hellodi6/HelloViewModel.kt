package com.example.hellodi6

import androidx.lifecycle.ViewModel


class HelloViewModel : ViewModel() {
    private val cryptocurrencies = listOf("Bitcoin", "Ethereum", "Litecoin")

    fun getCryptocurrencies() = cryptocurrencies
}