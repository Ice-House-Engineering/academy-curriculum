package com.example.hellonetworking7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread


const val LOG = "android-networking"

// https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
// Code for server: Common/Backend/code/HelloBackend7
class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uploadForm()
    }

    fun uploadForm() {
        val requestBody = FormBody.Builder()
            .add("name", "john woo")
            .add("age", "23")
            .build()
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
