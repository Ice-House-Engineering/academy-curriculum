package com.example.hellopaging1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.hellopaging1.model.CryptocurrenciesDao
import com.example.hellopaging1.model.CryptocurrenciesDatabase
import com.example.hellopaging1.model.CryptocurrencyModel


class CryptocurrenciesViewModel(app: Application): AndroidViewModel(app) {

    private val database : CryptocurrenciesDatabase
    private val cryptocurrenciesDao : CryptocurrenciesDao
    private val cryptocurrencies : LiveData<PagedList<CryptocurrencyModel>>

    init {
        database = CryptocurrenciesDatabase.getInstance(app)
        cryptocurrenciesDao = database.cryptocurrenciesDao()
        cryptocurrencies = cryptocurrenciesDao.getAllCryptocurrencies().toLiveData(pageSize=10)
    }

    fun getCryptocurrencies(): LiveData<PagedList<CryptocurrencyModel>> = cryptocurrencies
}