package com.example.helloretrofit6

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


interface ImageApi {

    // Replace this url with something else, if the image does not exist anymore
    @GET("/photo-1573108037329-37aa135a142e?auto=format&fit=crop&w=800")
    fun getImage(): Call<ResponseBody>
}