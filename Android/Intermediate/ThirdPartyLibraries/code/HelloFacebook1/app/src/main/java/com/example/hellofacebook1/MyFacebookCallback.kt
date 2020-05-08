package com.example.hellofacebook1

import android.os.Bundle
import android.widget.TextView
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.login.LoginResult
import org.json.JSONObject


class MyFacebookCallback(private val textView: TextView) : FacebookCallback<LoginResult> {
    override fun onSuccess(result: LoginResult?) {
        result?.let {
            val accessToken = result.accessToken
            val graphRequest = GraphRequest.newMeRequest(accessToken) { jsonObject: JSONObject, graphResponse: GraphResponse ->
                textView.text = jsonObject.getString("email")
            }
            val parameters = Bundle()
            parameters.putString("fields", "id,name,email")
            graphRequest.parameters = parameters
            graphRequest.executeAsync()
        }
    }

    override fun onCancel() {
    }

    override fun onError(error: FacebookException?) {
    }
}