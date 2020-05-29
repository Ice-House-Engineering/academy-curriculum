package com.example.hellonetworking10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.concurrent.thread


const val LOG = "android-networking"

// The code of the backend can be found in Common/Backend/code/HelloBackend9
class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login()
    }

    fun login() {
        val gson = Gson()
        val userAuthJson = UserAuthJson("john", "password")
        val jsonString = gson.toJson(userAuthJson)

        val jsonMediaType = "application/json".toMediaTypeOrNull()
        val requestBody = jsonString.toRequestBody(jsonMediaType)

        val request = Request.Builder()
            .url("http://10.0.2.2:5000/auth")
            .post(requestBody)
            .build()

        thread {
            client.newCall(request).execute().use { response ->
                val string = response.body!!.string()
                val token = gson.fromJson(string, TokenJson::class.java)
                getData(token.access_token)
            }
        }
    }

    fun getData(token: String) {
        val request = Request.Builder()
            .addHeader("Authorization", "JWT $token")
            .url("http://10.0.2.2:5000/secret")
            .build()

        client.newCall(request).execute().use { response ->
            val string = response.body!!.string()
            Log.d(LOG, string)
        }
    }
}
