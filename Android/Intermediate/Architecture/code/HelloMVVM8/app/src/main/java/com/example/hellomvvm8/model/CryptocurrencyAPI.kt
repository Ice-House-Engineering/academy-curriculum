package com.example.hellomvvm8.model

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class CryptocurrencyAPI {

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val api = retrofit.create(Cryptocurrency::class.java)

        fun downloadCryptocurrenciesList() : Observable<Map<String, Map<String, String>>> =
            api.requestCryptocurrenciesList()

        fun getCryptocurrency(name: String) : Observable<Map<String, String>> =
            api.requestCryptocurrency(name)

        fun validateCryptocurrencyNameDoesNotExist(name: String) : Boolean {
            val cryptocurrency = api.requestSyncCryptocurrency(name).execute().body()
            return cryptocurrency == null
        }

        fun createCryptocurrency(name: String, title: String) : Map<String, String> {
            val cryptocurrency = api.requestCreateCryptocurrency(name, title).execute().body()
            return cryptocurrency!!
        }

    }
}