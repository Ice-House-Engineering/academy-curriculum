package com.example.hellomvvm8.model

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface Cryptocurrency {
    @GET("/cryptocurrencies")
    fun requestCryptocurrenciesList(): Observable<Map<String, Map<String, String>>>

    @POST("/cryptocurrencies")
    @FormUrlEncoded
    fun requestCreateCryptocurrency(@Field("name") name: String, @Field("title") title: String): Call<Map<String, String>>

    @GET("/cryptocurrencies/{cryptocurrency_id}")
    fun requestCryptocurrency(@Path("cryptocurrency_id") cryptocurrency_id: String): Observable<Map<String, String>>

    @GET("/cryptocurrencies/{cryptocurrency_id}")
    fun requestSyncCryptocurrency(@Path("cryptocurrency_id") cryptocurrency_id: String): Call<Map<String, String>>
}