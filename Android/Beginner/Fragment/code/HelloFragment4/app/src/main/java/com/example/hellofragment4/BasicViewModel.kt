package com.example.hellofragment4

import androidx.lifecycle.ViewModel


class BasicViewModel : ViewModel() {
    private val cryptocurrencies = arrayOf("bitcoin", "ethereum", "monero", "bitcoin cash", "litecoin", "eos", "zcash")
    val cryptocurrency = cryptocurrencies.random()
}