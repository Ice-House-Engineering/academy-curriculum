# Cryptography

Cryptography is a big ecosystem. There is an encryption, a message digest, a signature, a message authentication code.

All of these codes use extra dependency.

Edit build.gradle (Module: app).

Add this dependency.
```gradle
implementation "androidx.security:security-crypto:1.0.0-beta01"
```

## Encryption

### Symmetrical Encryption

It means we use the same key to encrypt and decrypt the payload.

Create a new empty Activity. Name it HelloCryptography1.

Edit app / java / com.example.hellocryptography1 / MainActivity.
```kotlin
package com.example.hellocryptography1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.File


const val LOG = "hello-cryptography"

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
```

To get the alias of the master key, we use MasterKeys object.
```kotlin
private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
```

We use AES encryption algorithm.

https://en.wikipedia.org/wiki/Advanced_Encryption_Standard

There are other symmetric cryptography algorithms, such as 3DES.

https://en.wikipedia.org/wiki/Triple_DES

GCM means Galois/Counter Mode.

https://en.wikipedia.org/wiki/Galois/Counter_Mode

Then we create an encrypted file object.
```kotlin
val encryptedFile = EncryptedFile.Builder(
        File(applicationContext.filesDir, file),
        applicationContext,
        masterKeyAlias,
        EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
).build()
```

The builder receives the location of the encrypted file, the context, the alias of the master key, and the encryption scheme.

Then to write the content of the encrypted file, we can use buffered writer.
```kotlin
encryptedFile.openFileOutput().bufferedWriter().use {
    it.write("Hello Jakarta")
}
```

Then to read the content of the encrypted file, we can use buffered reader.
```kotlin
val contents = encryptedFile.openFileInput().bufferedReader().readLine()
```

If we ran the application, we would get this output in the log.
```
2020-03-20 11:54:42.310 24661-24661/com.example.hellocryptography1 D/hello-cryptography: Hello Jakarta
```

### String Symmetrical Encryption

To encrypt the string, not the file, we can use the instance of the Cipher.

Create an empty Activity project. Name it HelloCryptography2.

Edit app / java / com.example.hellocryptography2 / MainActivity.
```kotlin
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
```

To generate the key, we can use KeyGenerator.
```kotlin
fun generateKey() : SecretKey {
    val keygen = KeyGenerator.getInstance("AES")
    keygen.init(256)
    return keygen.generateKey()
}
```

There are other algorithms for generating keys beside AES: AESWRAP, ARC4, BLOWFISH, ChaCha20, DES, etc.

https://developer.android.com/guide/topics/security/cryptography#SupportedKeyGenerator

Then we need the initialization vector (IV). This is the randomized bytes used in the first block of the block encryption.

So to decrypt the payload, we need both the key and the initialization vector.

We can generate IV with SecureRandom object.
```kotlin
fun generateIv() : ByteArray {
    val random = SecureRandom()
    val iv = ByteArray(16)
    random.nextBytes(iv)
    return iv
}
```

Before encrypting and decrypting, we need to get the instance of Cipher.
```kotlin
private val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
```

There are other algorithms for Cipher other than AES/CBC/PKCS5PADDING. The AES is the algorithm, the CBC is the Modes, the PKCS5PADDING is the Padding.

The complete combination can be seen here:

https://developer.android.com/guide/topics/security/cryptography#SupportedCipher

To encrypt the payload, we can init the Cipher in encrypt mode before calling “doFinal” method.
```kotlin
fun encrypt(plaintext: ByteArray, key: SecretKey, iv: ByteArray) : ByteArray {
    val spec = IvParameterSpec(iv)

    cipher.init(Cipher.ENCRYPT_MODE, key, spec)
    return cipher.doFinal(plaintext)
}
```

To decrypt the payload, we can init the Cipher in decrypt mode before callign “doFinal” method.
```kotlin
fun decrypt(ciphertext: ByteArray, key: SecretKey, iv: ByteArray) : ByteArray {
    val spec = IvParameterSpec(iv)

    cipher.init(Cipher.DECRYPT_MODE, key, spec)
    return cipher.doFinal(ciphertext)
}
```

If we ran the application, we would get this log.
```
2020-03-20 17:28:08.226 28553-28553/com.example.hellocryptography2 D/hello-cryptography: [B@a5f90a3
2020-03-20 17:28:08.227 28553-28553/com.example.hellocryptography2 D/hello-cryptography: Hello Jakarta
```

## Asymmetric Cryptography

We can sign message to make sure its authenticity using asymmetric cryptography (public-private keys).

Create a new empty Activity project. Name it HelloCryptography3.

Edit app / java / com.example.hellocryptography3 / MainActivity.
```kotlin
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
```

First, if we don’t have public and private keys, we must generate them using KeyPairGenerator.
```kotlin
fun generateKeyPair() : KeyPair {
    val kpg = KeyPairGenerator.getInstance("DSA")
    kpg.initialize(2048)
    return kpg.generateKeyPair()
}
```

The “getInstance” method from “KeyPairGenerator” accepts algorithm. 

There are other algorithms like “DH”, “EC”, “RSA”.

https://developer.android.com/guide/topics/security/cryptography#SupportedKeyPairGenerator

The keysize is depended according to the algorithm.

We can find the correct keysize in this link:

https://docs.oracle.com/javase/10/docs/api/java/security/KeyPairGenerator.html

Then we can use private key to sign the message and the public key to verify the message and its signature.
```kotlin
val keypair = generateKeyPair()
val privateKey = keypair.private
val publicKey = keypair.public
val plaintext: ByteArray = "HelloJakarta".toByteArray(Charsets.UTF_8)
val signature = signMessage(plaintext, privateKey)
val verified = verifyMessage(plaintext, signature, publicKey)
```

To sign the message, we can use the instance of Signature and its methods like “initSign” and “update”.
```kotlin
fun signMessage(message: ByteArray, privateKey: PrivateKey): ByteArray {
    val s = Signature.getInstance("SHA256withDSA").apply {
        initSign(privateKey)
        update(message)
    }
    return s.sign()
}
```

The algorithm in Signature must match with the algorithm in KeyPairGenerator. “DSA” matches with “SHA256withDSA”.

The full list of algorithms of Signature can be found here:

https://developer.android.com/guide/topics/security/cryptography#SupportedSignature

The byte object of the signature can be gotten from the “sign” method.

To verify the message and its signature, we can use the instance of Signature and its methods like “initVerify” and “update” methods.
```kotlin
fun verifyMessage(message: ByteArray, signature: ByteArray, publicKey: PublicKey): Boolean {
    val s = Signature.getInstance("SHA256withDSA").apply {
        initVerify(publicKey)
        update(message)
    }
    return s.verify(signature)
}
```

To verify the message we can use “verify” method.

If we ran the application, we would get this log.
```
2020-03-20 18:34:42.664 29439-29439/? D/hello-cryptography: true
```

## Message Digest

Message digest is hashing. Hashing is producing input with any length to output with a fixed length. Hashing is like a commitment or pointer.

Create an empty Activity project. Name it HelloCryptography4.

Edit app / java / com.example.hellocryptography4 / MainActivity.
```kotlin
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
```

We get the instance of MessageDigest. We have to supply the algorithms.
```kotlin
val md = MessageDigest.getInstance("SHA-256")
```

There are many algorithms like: MD5, SHA-1, SHA-224, etc.

https://developer.android.com/guide/topics/security/cryptography#SupportedMessageDigest

Then we digest out input to the hash output.
```kotlin
val digest: ByteArray = md.digest(plaintext)
```

Usually people like to see the output of the hashing in hexadecimal format.
```kotlin
val hexstring = digest.joinToString("") { "%02x".format(it) }
```

If we ran the application, we would get this log.
```
2020-03-20 22:01:43.019 31637-31637/? D/hello-cryptography: 1b575fa89533a2ea440423663ad6f994dce64cecac48798daab2d4e4964bd0d9
```

## Encrypted Shared Preferences

We can use the encrypted shared preferences.

Create a new empty Activity and name it HelloCryptography5.

Edit app / java / com.example.hellocryptography5 / MainActivity.
```kotlin
package com.example.hellocryptography5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


const val LOG = "hello-cryptography"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        val sharedPreferences = EncryptedSharedPreferences.create(
                "encrypted_shared_preferences",
                masterKeyAlias,
                applicationContext,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        with(sharedPreferences.edit()) {
            putInt("number", 1)
            commit()
        }

        Thread.sleep(1000)

        val value = sharedPreferences.getInt("number", 0)
        Log.d(LOG, value.toString())
    }
}
```

The difference to the normal SharedPreferences is we use master key alias and EncryptedSharedPreferences.
```kotlin
val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

val sharedPreferences = EncryptedSharedPreferences.create(
        "encrypted_shared_preferences",
        masterKeyAlias,
        applicationContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
)
```

If we ran the application, we would get this log.
```
2020-03-21 18:52:44.594 3463-3463/com.example.hellocryptography5 D/hello-cryptography: 1
```

# Optional Readings

https://developer.android.com/guide/topics/security/cryptography

https://developer.android.com/training/articles/keystore

# Exercises

1. Use Keystore to encrypt and decrypt message or file.