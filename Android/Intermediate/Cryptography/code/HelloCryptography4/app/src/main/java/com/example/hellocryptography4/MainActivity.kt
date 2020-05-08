package com.example.hellocryptography4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.security.MessageDigest


const val LOG = "hello-cryptography"

// Code adapted from https://developer.android.com/guide/topics/security/cryptography
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val plaintext: ByteArray = "Hello Jakarta".toByteArray(Charsets.UTF_8)
        val md = MessageDigest.getInstance("SHA-256")
        val digest: ByteArray = md.digest(plaintext)

        val hexstring = digest.joinToString("") { "%02x".format(it) }
        Log.d(LOG, hexstring)
    }
}
