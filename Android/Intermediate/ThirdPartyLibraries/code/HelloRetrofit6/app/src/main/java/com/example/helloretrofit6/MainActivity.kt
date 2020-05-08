package com.example.helloretrofit6

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        downloadImageWithProgress()
    }

    fun downloadImageWithProgress() {
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(DownloadProgressInterceptor())
            .build()

        // Replace this url with something else, if the image does not exist anymore
        val retrofit = Retrofit.Builder()
            .baseUrl("https://images.unsplash.com")
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val imageApi = retrofit.create(ImageApi::class.java)

        val call = imageApi.getImage()

        thread {
            val imageBody = call.execute().body()
            val imageBytes = imageBody!!.byteStream()
            val image = BitmapFactory.decodeStream(imageBytes)
            runOnUiThread {
                imageView!!.setImageBitmap(image)
            }
        }
    }
}
