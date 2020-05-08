package com.example.helloretrofit3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.concurrent.thread


const val LOG = "android-retrofit"

class MainActivity : AppCompatActivity() {

    // The server code is in Common/Backend/code/HelloBackend9
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val jwtAuthenticationApi = retrofit.create(JwtAuthentication::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getToken()
    }

    fun getToken() {
        val call = jwtAuthenticationApi.getToken(LoginRequest("john", "password"))
        thread {
            val token = call.execute().body()
            token?.get("access_token").let {
                Log.d(LOG, it)
                getSecret(it!!)
            }
        }
    }

    fun getSecret(tokenString: String) {
        val jwtTokenString = "JWT " + tokenString
        val call = jwtAuthenticationApi.getSecret(jwtTokenString)
        val secret = call.execute().body()
        Log.d(LOG, secret)
    }
}
