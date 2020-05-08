package com.example.hellofragment5

import androidx.lifecycle.ViewModel


class DetailViewModel : ViewModel() {
    private var model: CryptocurrencyModel? = null
    fun getModel(id: Int) = model ?: CryptocurrencyRepository.findCryptocurrencyById(id).also { model = it }
}