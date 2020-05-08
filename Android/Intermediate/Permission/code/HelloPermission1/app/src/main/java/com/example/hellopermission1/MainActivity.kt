package com.example.hellopermission1

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


const val LOG = "hello-permission"

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_PERMISSION_READ_CONTACTS = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            Log.d(LOG, "Permission for reading contacts is granted")
        } else {
            Log.d(LOG, "Permission for reading contacts is not granted")

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                Log.d(LOG, "You should grant permission for reading contacts")
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE_PERMISSION_READ_CONTACTS)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE_PERMISSION_READ_CONTACTS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(LOG, "User grants read contact permission")
                } else {
                    Log.d(LOG, "User does not grand read contact permission")
                }
                return
            }

            else -> {
                Log.d(LOG, "Something different happened")
            }
        }
    }
}
