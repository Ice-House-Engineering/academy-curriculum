package com.example.helloretrofit3

import retrofit2.Call
import retrofit2.http.*


data class LoginRequest(val username: String, val password: String)

interface JwtAuthentication {

    @POST("/auth")
    @Headers("Content-Type: application/json")
    fun getToken(@Body login: LoginRequest): Call<Map<String, String>>

    @GET("/secret")
    fun getSecret(@Header("Authorization") token: String): Call<String>
}