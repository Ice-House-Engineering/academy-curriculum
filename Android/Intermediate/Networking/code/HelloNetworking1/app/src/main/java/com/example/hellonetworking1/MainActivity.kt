package com.example.hellonetworking1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.*
import java.io.IOException
import kotlin.concurrent.thread


const val LOG = "networking-"

// From https://square.github.io/okhttp/
// https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
// Code for server: Common/Backend/code/HelloBackend1
class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        synchronousDownload()
        Thread.sleep(500)
        asynchronousDownload()
    }

    fun synchronousDownload() {
        val l = "synchronous"
        val request = Request.Builder()
            .url("http://10.0.2.2:5000")
            .build()
        thread {
            client.newCall(request).execute().use { response ->
                Log.d(LOG + l, response.isSuccessful.toString())
                Log.d(LOG + l, response.headers.toString())
                Log.d(LOG + l, response.headers["Content-Type"]!!)
                Log.d(LOG + l, response.body!!.string())
            }
            Log.d(LOG + l, "After calling the synchronous downloading data process...")
        }
    }

    fun asynchronousDownload() {
        val l = "asynchronous"
        val request = Request.Builder()
            .url("http://10.0.2.2:5000")
            .build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(LOG + l, response.isSuccessful.toString())
                Log.d(LOG + l, response.headers.toString())
                Log.d(LOG + l, response.headers["Content-Type"]!!)
                Log.d(LOG + l, response.body!!.string())
            }
        })
        Log.d(LOG + l, "After calling the asynchronous downloading data process...")
    }
}
