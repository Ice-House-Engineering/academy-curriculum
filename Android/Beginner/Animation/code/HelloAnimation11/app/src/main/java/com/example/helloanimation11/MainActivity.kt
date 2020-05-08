package com.example.helloanimation11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newConstraint = ConstraintSet()
        newConstraint.clone(this, R.layout.activity_main2)

        val root : ConstraintLayout = findViewById(R.id.constraintLayout)

        val button : Button = findViewById(R.id.button)
        button.setOnClickListener {
            TransitionManager.beginDelayedTransition(root)
            newConstraint.applyTo(root)
        }
    }
}
