package com.example.hellocryptography2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec


const val LOG = "hello-cryptography"

class MainActivity : AppCompatActivity() {

    private val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val key = generateKey()
        val iv = generateIv()
        val plaintext: ByteArray = "Hello Jakarta".toByteArray(Charsets.UTF_8)

        val encryptedtext = encrypt(plaintext, key, iv)
        Log.d(LOG, encryptedtext.toString())

        val decryptedtext = decrypt(encryptedtext, key, iv)
        Log.d(LOG, decryptedtext.toString(Charsets.UTF_8))
    }

    fun generateKey() : SecretKey {
        val keygen = KeyGenerator.getInstance("AES")
        keygen.init(256)
        return keygen.generateKey()
    }

    fun generateIv() : ByteArray {
        val random = SecureRandom()
        val iv = ByteArray(16)
        random.nextBytes(iv)
        return iv
    }

    fun encrypt(plaintext: ByteArray, key: SecretKey, iv: ByteArray) : ByteArray {
        val spec = IvParameterSpec(iv)

        cipher.init(Cipher.ENCRYPT_MODE, key, spec)
        return cipher.doFinal(plaintext)
    }

    fun decrypt(ciphertext: ByteArray, key: SecretKey, iv: ByteArray) : ByteArray {
        val spec = IvParameterSpec(iv)

        cipher.init(Cipher.DECRYPT_MODE, key, spec)
        return cipher.doFinal(ciphertext)
    }
}
