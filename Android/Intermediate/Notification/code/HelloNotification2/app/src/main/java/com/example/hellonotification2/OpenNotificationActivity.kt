package com.example.hellonotification2

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput


const val STRING_INPUT = "STRING_INPUT"

class OpenNotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_notification)

        val remoteInput = RemoteInput.getResultsFromIntent(intent)

        if (remoteInput!=null) {
            val inputString = remoteInput.getCharSequence(KEY_TEXT_REPLY).toString()
            createNotification(inputString)
        } else {
            val textView = findViewById<TextView>(R.id.textView)
            textView.text = intent.getStringExtra(STRING_INPUT)
        }
    }

    private fun createNotification(string : String) {
        val intent = Intent(this, OpenNotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(STRING_INPUT, string)
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText("Reply sent")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationId = 0

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }
}