package com.example.hellonetworking5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.concurrent.thread


const val LOG = "android-networking"

// https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
// Code for server: Common/Backend/code/HelloBackend5
class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uploadJson()
    }

    fun uploadJson() {
        val gson = Gson()
        val cryptocurrencyJson = CryptocurrencyJson("bitcoin")
        val jsonString = gson.toJson(cryptocurrencyJson)

        val jsonMediaType = "application/json".toMediaTypeOrNull()
        val requestBody = jsonString.toRequestBody(jsonMediaType)

        val request = Request.Builder()
            .url("http://10.0.2.2:5000")
            .post(requestBody)
            .build()

        thread {
            client.newCall(request).execute().use { response ->
                val string = response.body!!.string()
                Log.d(LOG, string)
            }
        }
    }
}
