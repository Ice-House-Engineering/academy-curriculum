package com.example.hellogesture1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View

const val ACTIVITY_DEBUG_TAG = "activity-gesture-tag"
const val VIEW_DEBUG_TAG = "view-gesture-tag"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.view).setOnTouchListener { v, event ->
            val action: Int = event.actionMasked

            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d(VIEW_DEBUG_TAG, "Action was DOWN")
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.d(VIEW_DEBUG_TAG, "Action was MOVE")
                    true
                }
                MotionEvent.ACTION_UP -> {
                    Log.d(VIEW_DEBUG_TAG, "Action was UP")
                    true
                }
                MotionEvent.ACTION_CANCEL -> {
                    Log.d(VIEW_DEBUG_TAG, "Action was CANCEL")
                    true
                }
                MotionEvent.ACTION_OUTSIDE -> {
                    Log.d(VIEW_DEBUG_TAG, "Movement occurred outside bounds of current screen element")
                    true
                }
                else -> super.onTouchEvent(event)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val action: Int = event.actionMasked

        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(ACTIVITY_DEBUG_TAG, "Action was DOWN")
                true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(ACTIVITY_DEBUG_TAG, "Action was MOVE")
                true
            }
            MotionEvent.ACTION_UP -> {
                Log.d(ACTIVITY_DEBUG_TAG, "Action was UP")
                true
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.d(ACTIVITY_DEBUG_TAG, "Action was CANCEL")
                true
            }
            MotionEvent.ACTION_OUTSIDE -> {
                Log.d(
                    ACTIVITY_DEBUG_TAG,
                    "Movement occurred outside bounds of current screen element"
                )
                true
            }
            else -> super.onTouchEvent(event)
        }
    }

}
