package com.example.hellomvvm9.model

import retrofit2.Retrofit


class CryptocurrencyAPI(retrofit: Retrofit) {

    private val api = retrofit.create(Cryptocurrency::class.java)

    fun downloadCryptocurrenciesList() : Map<String, Map<String, String>>? {
        val call = api.requestCryptocurrenciesList()
        val cryptocurrenciesList = call.execute().body()
        return cryptocurrenciesList
    }

    fun getCryptocurrency(name: String) : Map<String, String> {
        val call = api.requestCryptocurrency(name)
        val cryptocurrency = call.execute().body()
        cryptocurrency?.let {
            return it
        }
        return emptyMap()
    }

    fun createCryptocurrency(name: String, title: String) : Map<String, String> {
        val call = api.requestCreateCryptocurrency(name, title)
        val cryptocurrency = call.execute().body()
        cryptocurrency?.let {
            return it
        }
        return emptyMap()
    }
}