package com.example.helloanimation8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setOnClickListener{
            val imagePair = Pair.create(imageView as View, "transitionImage")
            val options =  ActivityOptionsCompat.makeSceneTransitionAnimation(this, imagePair)
            val intent = Intent(this, Main2Activity::class.java)
            ActivityCompat.startActivity(this, intent, options.toBundle())
        }
    }
}
