package com.example.hellomvvm6.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.hellomvvm6.model.CryptocurrenciesDao
import com.example.hellomvvm6.model.CryptocurrencyModel
import com.example.hellomvvm6.model.CryptocurrenciesDatabase
import java.util.*


class CryptocurrenciesViewModel(app: Application) : AndroidViewModel(app) {

    private val database : CryptocurrenciesDatabase
    private val cryptocurrenciesDao : CryptocurrenciesDao
    private val cryptocurrencies : LiveData<List<CryptocurrencyModel>>

    init {
        database = CryptocurrenciesDatabase.getInstance(app)
        cryptocurrenciesDao = database.cryptocurrenciesDao()
        cryptocurrencies = cryptocurrenciesDao.getAllCryptocurrencies()
    }

    fun addToCryptocurrencies(name : String, title : String) {
        val upperCaseName = name.toUpperCase(Locale.getDefault())
        val newCryptocurrency = CryptocurrencyModel(id=0, name=upperCaseName, title=title)
        cryptocurrenciesDao.insertCryptocurrency(newCryptocurrency)
    }

    fun validateNameAndTitle(name : String, title : String) : Boolean {
        if (name.isEmpty() or title.isEmpty()) {
            return false
        }
        return true
    }

    fun validateCryptocurrencyName(name : String) : Boolean {
        return !cryptocurrenciesDao.cryptocurrencyExists(name.toUpperCase(Locale.getDefault()))
    }

    fun getCryptocurrencies(): LiveData<List<CryptocurrencyModel>> = cryptocurrencies

    fun findCryptocurrency(name: String) : LiveData<List<CryptocurrencyModel>> = cryptocurrenciesDao.findCryptocurrency(name)
}