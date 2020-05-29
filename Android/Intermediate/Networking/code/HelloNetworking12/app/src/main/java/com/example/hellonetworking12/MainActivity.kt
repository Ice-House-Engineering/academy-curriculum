package com.example.hellonetworking12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread


const val LOG = "networking-"

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = Request.Builder()
                .url("http://10.0.2.2:8765")
                .build()

        thread {
            client.newWebSocket(request, MyWebSocketListener())
        }
    }
}
