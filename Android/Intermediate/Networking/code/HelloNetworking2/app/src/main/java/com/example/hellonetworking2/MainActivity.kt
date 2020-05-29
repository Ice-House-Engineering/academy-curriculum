package com.example.hellonetworking2

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread


// From https://square.github.io/okhttp/
// https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
// Code for server: Common/Backend/code/HelloBackend2
class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        downloadImage()
    }

    fun downloadImage() {
        val request = Request.Builder()
            .url("http://10.0.2.2:5000/image")
            .build()
        thread {
            client.newCall(request).execute().use { response ->
                val imageBytes = response.body!!.byteStream()
                val image = BitmapFactory.decodeStream(imageBytes)
                runOnUiThread {
                    imageView!!.setImageBitmap(image)
                }
            }
        }
    }
}
