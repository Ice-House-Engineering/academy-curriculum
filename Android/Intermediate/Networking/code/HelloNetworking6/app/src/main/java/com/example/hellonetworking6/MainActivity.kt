package com.example.hellonetworking6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread


const val LOG = "networking-"

// https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
// Code for server: Common/Backend/code/HelloBackend6
class MainActivity : AppCompatActivity() {

    private val clientBuilder = OkHttpClient().newBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        followRedirect()
        dontFollowRedirect()
    }

    fun followRedirect() {
        val request = Request.Builder()
            .url("http://10.0.2.2:5000")
            .build()

        val client = clientBuilder.build()

        thread {
            client.newCall(request).execute().use { response ->
                val string = response.body!!.string()
                Log.d(LOG + "redirect", string)
            }
        }
    }

    fun dontFollowRedirect() {
        val request = Request.Builder()
            .url("http://10.0.2.2:5000")
            .build()

        val client = clientBuilder.followRedirects(false).build()

        thread {
            client.newCall(request).execute().use { response ->
                val string = response.body!!.string()
                Log.d(LOG + "not-redirect", string)
            }
        }
    }
}
