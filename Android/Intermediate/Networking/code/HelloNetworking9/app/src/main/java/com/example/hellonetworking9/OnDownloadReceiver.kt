package com.example.hellonetworking9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


const val LOG = "download-manager"

class OnDownloadReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(LOG, "Download Complete")
    }
}