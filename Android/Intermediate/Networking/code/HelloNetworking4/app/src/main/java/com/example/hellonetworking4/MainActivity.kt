package com.example.hellonetworking4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread


const val LOG = "android-networking"

// https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
// Code for server: Common/Backend/code/HelloBackend4
class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadJson()
    }

    fun downloadJson() {
        val request = Request.Builder()
            .url("http://10.0.2.2:5000")
            .build()

        thread {
            client.newCall(request).execute().use { response ->
                val string = response.body!!.string()
                val gson = Gson()
                val cryptocurrencyJson = gson.fromJson(string, CryptocurrencyJson::class.java)
                Log.d(LOG, cryptocurrencyJson.toString())
            }
        }
    }

}
