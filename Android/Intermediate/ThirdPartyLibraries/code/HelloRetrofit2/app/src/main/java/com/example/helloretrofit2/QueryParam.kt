package com.example.helloretrofit2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface QueryParam {

    @GET("/")
    fun requestUpperName(@Query("name") lowerName: String): Call<String>
}