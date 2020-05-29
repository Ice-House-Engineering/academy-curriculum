package com.example.hellomvvm7.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hellomvvm7.model.CryptocurrencyAPI
import com.example.hellomvvm7.model.CryptocurrencyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.*


class CryptocurrenciesViewModel: ViewModel() {

    private val job = Job()

    val scope = CoroutineScope(Dispatchers.IO + job)

    fun getCryptocurrencies() : Map<String, Map<String, String>>? {
        return CryptocurrencyAPI.downloadCryptocurrenciesList()
    }

    fun getCryptocurrency(name: String) : CryptocurrencyModel {
        val cryptocurrency = CryptocurrencyAPI.getCryptocurrency(name)
        cryptocurrency["name"]?.let {
            return CryptocurrencyModel(it, cryptocurrency["title"])
        }
        return CryptocurrencyModel("", "")
    }

    fun validateNameAndTitle(name : String, title : String) : Boolean {
        if (name.isEmpty() or title.isEmpty()) {
            return false
        }
        return true
    }

    fun validateCryptocurrencyName(name : String) : Boolean {
        return CryptocurrencyAPI.getCryptocurrency(name.toUpperCase(Locale.getDefault())).isEmpty()
    }

    fun addToCryptocurrencies(name : String, title : String) {
        val upperCaseName = name.toUpperCase(Locale.getDefault())
        CryptocurrencyAPI.createCryptocurrency(upperCaseName, title)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}