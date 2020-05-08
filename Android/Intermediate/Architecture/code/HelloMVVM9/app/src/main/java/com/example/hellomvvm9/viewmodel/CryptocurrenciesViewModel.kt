package com.example.hellomvvm9.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hellomvvm9.model.CryptocurrencyAPI
import com.example.hellomvvm9.model.CryptocurrencyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import retrofit2.Retrofit
import java.util.*


class CryptocurrenciesViewModel(): ViewModel(), KoinComponent {

    val retrofit : Retrofit by inject(named("RETROFIT"))
    private val cryptocurrencyAPI : CryptocurrencyAPI

    private val job = Job()

    val scope = CoroutineScope(Dispatchers.IO + job)

    init {
        cryptocurrencyAPI = CryptocurrencyAPI(retrofit)
    }

    fun getCryptocurrencies() : Map<String, Map<String, String>>? {
        return cryptocurrencyAPI.downloadCryptocurrenciesList()
    }

    fun getCryptocurrency(name: String) : CryptocurrencyModel {
        val cryptocurrency = cryptocurrencyAPI.getCryptocurrency(name)
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
        return cryptocurrencyAPI.getCryptocurrency(name.toUpperCase(Locale.getDefault())).isEmpty()
    }

    fun addToCryptocurrencies(name : String, title : String) {
        val upperCaseName = name.toUpperCase(Locale.getDefault())
        cryptocurrencyAPI.createCryptocurrency(upperCaseName, title)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}