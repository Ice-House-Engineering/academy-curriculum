package com.example.helloretrofit5

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ImageApi {

    @Multipart
    @POST("/upload_image")
    fun uploadImage(@Part image: MultipartBody.Part): Call<String>
}