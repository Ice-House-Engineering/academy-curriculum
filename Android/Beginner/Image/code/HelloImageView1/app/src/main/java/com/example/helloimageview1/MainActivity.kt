package com.example.helloimageview1

import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val matrix = Matrix()
        matrix.postScale(0.05f, 0.1f)
        matrix.postRotate(45f)
        imageView.imageMatrix = matrix
    }
}
