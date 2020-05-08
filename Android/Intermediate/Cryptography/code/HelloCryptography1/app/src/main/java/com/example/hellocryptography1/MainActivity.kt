package com.example.hellocryptography1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.File


const val LOG = "hello-cryptography"

// Adapter from https://developer.android.com/guide/topics/security/cryptography
class MainActivity : AppCompatActivity() {

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
    private val file = "sensitive_data.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        encrypt()

        decrypt()
    }

    fun encrypt() {
        val encryptedFile = EncryptedFile.Builder(
                File(applicationContext.filesDir, file),
                applicationContext,
                masterKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        encryptedFile.openFileOutput().bufferedWriter().use {
            it.write("Hello Jakarta")
        }
    }

    fun decrypt() {
        val encryptedFile = EncryptedFile.Builder(
                File(applicationContext.filesDir, file),
                applicationContext,
                masterKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        val contents = encryptedFile.openFileInput().bufferedReader().readLine()

        Log.d(LOG, contents)
    }
}
