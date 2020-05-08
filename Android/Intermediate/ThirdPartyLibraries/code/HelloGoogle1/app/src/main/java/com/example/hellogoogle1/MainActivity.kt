package com.example.hellogoogle1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


private val RC_SIGN_IN = 0

class MainActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        account?.let {
            updateUI(it)
        }

        findViewById<SignInButton>(R.id.button).setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        findViewById<Button>(R.id.logOutButton).setOnClickListener {
            googleSignInClient.signOut().addOnCompleteListener {
                clearUI()
            }
        }

        findViewById<Button>(R.id.revokeButton).setOnClickListener {
            googleSignInClient.revokeAccess().addOnCompleteListener {
                clearUI()
            }
        }
    }

    fun updateUI(account: GoogleSignInAccount) {
        findViewById<TextView>(R.id.textView).text = account.email
    }

    fun clearUI() {
        findViewById<TextView>(R.id.textView).text = ""
    }

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        val account = completedTask.getResult(ApiException::class.java)
        account?.let {
            updateUI(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
}
