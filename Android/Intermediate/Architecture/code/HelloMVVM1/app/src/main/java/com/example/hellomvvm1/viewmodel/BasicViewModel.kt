package com.example.hellomvvm1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hellomvvm1.model.Database


class BasicViewModel : ViewModel() {

    fun setCryptocurrencies(newCryptocurrencies : String) {
        Database.setCryptocurrencies(newCryptocurrencies.toUpperCase())
    }

    fun getCryptocurrencies(): String = Database.getCryptocurrencies()

}