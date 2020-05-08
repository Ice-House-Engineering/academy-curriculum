package com.example.hellonotification3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId


const val LOG = "hello-notification"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener {
                    val token = it.result?.token
                    Log.d(LOG, token)
                }
    }
}
