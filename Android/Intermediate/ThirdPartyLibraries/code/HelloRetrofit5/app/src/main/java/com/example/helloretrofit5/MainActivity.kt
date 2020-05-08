package com.example.helloretrofit5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.ByteArrayOutputStream
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.concurrent.thread


const val LOG = "android-retrofit"

class MainActivity : AppCompatActivity() {

    // The server code is in Common/Backend/code/HelloBackend3
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private val imageApi = retrofit.create(ImageApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uploadImage()
    }

    fun uploadImage() {
        val bitmapImage = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.tree)
        val stream = ByteArrayOutputStream()
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 90, stream)
        val image = stream.toByteArray()

        val jpgMediaType = "image/jpg".toMediaTypeOrNull()
        val imageRequestBody = image.toRequestBody(jpgMediaType)

        val body = MultipartBody.Part.createFormData("file", "tree.jpg", imageRequestBody)

        val call = imageApi.uploadImage(body)
        thread {
            val result = call.execute().body()
            Log.d(LOG, result)
        }
    }
}
