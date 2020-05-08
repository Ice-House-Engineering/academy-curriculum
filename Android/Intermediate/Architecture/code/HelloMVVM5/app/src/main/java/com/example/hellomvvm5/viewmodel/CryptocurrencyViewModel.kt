package com.example.hellomvvm5.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hellomvvm5.model.CryptocurrencyModel
import com.example.hellomvvm5.model.Database


class CryptocurrencyViewModel : ViewModel() {
    private var model: CryptocurrencyModel? = null
    fun getModel(name: String) = model ?: Database.findCryptocurrency(name).also { model = it }
}