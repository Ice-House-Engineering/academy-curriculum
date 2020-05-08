package com.example.helloretrofit4

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


interface ImageApi {

    @GET("/image")
    fun getImage(): Call<ResponseBody>
}