package com.example.hellonetworking9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.app.DownloadManager
import android.content.Context
import android.content.IntentFilter
import android.net.Uri
import android.widget.Button


class MainActivity : AppCompatActivity() {

    val onDownloadReceiver = OnDownloadReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            download()
        }
    }

    fun download() {
        val imageUrl = "https://images.unsplash.com/photo-1573108037329-37aa135a142e?auto=format&fit=crop&w=800"
        val uri = Uri.parse(imageUrl)

        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val req = DownloadManager.Request(uri)
        req.setTitle("Downloading Image")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "tree.jpg")
            .setAllowedOverRoaming(false)
            .setDescription("Description of Downloading Image")
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE.xor(DownloadManager.Request.NETWORK_WIFI))
        manager.enqueue(req)
    }

    override fun onResume() {
        super.onResume()

        val filter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        registerReceiver(onDownloadReceiver, filter)
    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(onDownloadReceiver)
    }
}
