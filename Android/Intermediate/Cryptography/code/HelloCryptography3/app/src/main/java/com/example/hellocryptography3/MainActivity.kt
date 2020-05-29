package com.example.hellocryptography3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.security.*


const val LOG = "hello-cryptography"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keypair = generateKeyPair()
        val privateKey = keypair.private
        val publicKey = keypair.public
        val plaintext: ByteArray = "HelloJakarta".toByteArray(Charsets.UTF_8)
        val signature = signMessage(plaintext, privateKey)
        val verified = verifyMessage(plaintext, signature, publicKey)

        Log.d(LOG, verified.toString())
    }

    fun signMessage(message: ByteArray, privateKey: PrivateKey): ByteArray {
        val s = Signature.getInstance("SHA256withDSA").apply {
            initSign(privateKey)
            update(message)
        }
        return s.sign()
    }

    fun generateKeyPair() : KeyPair {
        val kpg = KeyPairGenerator.getInstance("DSA")
        kpg.initialize(2048)
        return kpg.generateKeyPair()
    }

    fun verifyMessage(message: ByteArray, signature: ByteArray, publicKey: PublicKey): Boolean {
        val s = Signature.getInstance("SHA256withDSA").apply {
            initVerify(publicKey)
            update(message)
        }
        return s.verify(signature)
    }
}
