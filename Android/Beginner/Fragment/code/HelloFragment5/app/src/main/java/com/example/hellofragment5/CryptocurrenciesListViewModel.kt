package com.example.hellofragment5

import androidx.lifecycle.ViewModel


class CryptocurrenciesListViewModel : ViewModel() {
    val cryptocurrencies = CryptocurrencyRepository.getCryptocurrencies()
}