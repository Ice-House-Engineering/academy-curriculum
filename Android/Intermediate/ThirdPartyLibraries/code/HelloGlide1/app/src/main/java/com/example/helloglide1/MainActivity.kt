package com.example.helloglide1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageUrl = "https://images.unsplash.com/photo-1573108037329-37aa135a142e?auto=format&fit=crop&w=800"

        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load(imageUrl)
            .override(200, 200)
            .into(imageView)

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val options = RequestOptions().centerCrop().override(200, 200)
        Glide.with(this)
            .load(imageUrl)
            .apply(options)
            .into(imageView2)

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        Glide.with(this)
            .load("")
            .override(200, 200)
            .placeholder(android.R.drawable.ic_lock_lock)
            .into(imageView3)
    }
}
