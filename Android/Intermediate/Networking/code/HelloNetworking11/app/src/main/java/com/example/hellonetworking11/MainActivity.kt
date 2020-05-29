package com.example.hellonetworking11

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import okhttp3.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private var imageView: ImageView? = null

    private val imageUrl = "https://images.unsplash.com/photo-1573108037329-37aa135a142e?auto=format&fit=crop&w=800"

    private lateinit var client: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonDownloadImage).setOnClickListener {
            downloadImage()
        }

        findViewById<Button>(R.id.buttonDownloadImageWithCache).setOnClickListener {
            downloadImageWithCache()
        }

        findViewById<Button>(R.id.buttonClearImage).setOnClickListener {
            clearImage()
        }

        findViewById<Button>(R.id.buttonClearCache).setOnClickListener {
            clearCache()
        }

        imageView = findViewById(R.id.imageView)

        client = OkHttpClient.Builder().let {
            val cacheSize: Long = 40 * 1024 * 1024
            val cache = Cache(applicationContext.cacheDir, cacheSize)
            it.cache(cache).build()
        }
    }

    fun downloadImage() {
        val request = Request.Builder()
            .url(imageUrl)
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

    fun downloadImageWithCache() {
        val request = Request.Builder()
            .cacheControl(CacheControl.FORCE_CACHE)  // CacheControl.FORCE_NETWORK is for disregarding cache and going to the network directly
            .url(imageUrl)
            .build()
        thread {
            client.newCall(request).execute().use { response ->
                response.body?.let {
                    val imageBytes = it.byteStream()
                    val image = BitmapFactory.decodeStream(imageBytes)
                    runOnUiThread {
                        imageView!!.setImageBitmap(image)
                    }
                }
            }
        }
    }

    fun clearImage() {
        imageView!!.setImageDrawable(null)
    }

    fun clearCache() {
        client.cache?.evictAll()
    }
}
