package com.example.hellomvvm3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hellomvvm3.model.Database
import java.util.*


class BasicViewModel : ViewModel() {

    private val cryptocurrency : MutableLiveData<String> = MutableLiveData(Database.getCryptocurrencies())

    fun setCryptocurrencies(newCryptocurrencies : String) {
        Database.setCryptocurrencies(newCryptocurrencies.toUpperCase(Locale.getDefault()))
        cryptocurrency.value = Database.getCryptocurrencies()
    }

    fun getCryptocurrencies(): LiveData<String> = cryptocurrency

}