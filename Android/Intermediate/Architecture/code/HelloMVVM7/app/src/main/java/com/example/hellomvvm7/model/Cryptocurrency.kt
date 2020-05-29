package com.example.hellomvvm7.model

import retrofit2.Call
import retrofit2.http.*


interface Cryptocurrency {
    @GET("/cryptocurrencies")
    fun requestCryptocurrenciesList(): Call<Map<String, Map<String, String>>>

    @POST("/cryptocurrencies")
    @FormUrlEncoded
    fun requestCreateCryptocurrency(@Field("name") name: String, @Field("title") title: String): Call<Map<String, String>>

    @GET("/cryptocurrencies/{cryptocurrency_id}")
    fun requestCryptocurrency(@Path("cryptocurrency_id") cryptocurrency_id: String): Call<Map<String, String>>
}