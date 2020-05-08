package com.example.helloretrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.concurrent.thread


const val LOG = "android-retrofit"

class MainActivity : AppCompatActivity() {

    // The server code is in Common/Backend/code/HelloBackend10
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private val upperStringApi = retrofit.create(QueryParam::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queryParameter()
    }

    fun queryParameter() {
        val call = upperStringApi.requestUpperName("james")
        thread {
            val upperString = call.execute().body()
            Log.d(LOG, upperString)
        }
    }
}
