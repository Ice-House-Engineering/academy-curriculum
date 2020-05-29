package com.example.hellofacebook1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.facebook.login.widget.LoginButton


const val EMAIL = "email"

class MainActivity : AppCompatActivity() {

    private lateinit var callbackManager: CallbackManager
    private lateinit var facebookCallback: MyFacebookCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<LoginButton>(R.id.button)
        loginButton.setPermissions(listOf(EMAIL))

        val textView = findViewById<TextView>(R.id.textView)

        callbackManager = CallbackManager.Factory.create()
        facebookCallback = MyFacebookCallback(textView)
        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
