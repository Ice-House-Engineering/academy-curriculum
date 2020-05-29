package com.example.hellomvvm5.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hellomvvm5.model.CryptocurrencyModel
import com.example.hellomvvm5.model.Database
import java.util.*


class CryptocurrenciesViewModel : ViewModel() {

    private val cryptocurrencies : MutableLiveData<MutableMap<Int, CryptocurrencyModel>> = MutableLiveData(Database.getCryptocurrencies())
    private val errorMode : MutableLiveData<Boolean> = MutableLiveData(false)

    fun validateCryptocurrencies(name : String, title : String) {
        val upperCasedName = name.toUpperCase(Locale.getDefault())
        errorMode.value = (upperCasedName.isEmpty() or title.isEmpty() or (Database.findCryptocurrency(upperCasedName) != null))
    }

    fun clearError() {
        errorMode.value = false
    }

    fun addToCryptocurrencies(name : String, title : String) {
        val upperCaseName = name.toUpperCase(Locale.getDefault())
        val newCryptocurrency = CryptocurrencyModel(upperCaseName, title)
        Database.addToCryptocurrencies(newCryptocurrency)
        cryptocurrencies.value = Database.getCryptocurrencies()
    }

    fun getCryptocurrencies(): LiveData<MutableMap<Int, CryptocurrencyModel>> = cryptocurrencies

    fun isError(): LiveData<Boolean> = errorMode
}