package com.example.hellodialog2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity(), PassParameterToActivityDialogFragment.PassParameterToActivityListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.buttonDialog1).setOnClickListener {
            SimpleDialogFragment().show(supportFragmentManager, "simple dialog")
        }
        findViewById<Button>(R.id.buttonDialog2).setOnClickListener {
            ListDialogFragment().show(supportFragmentManager, "list dialog")
        }
        findViewById<Button>(R.id.buttonDialog3).setOnClickListener {
            MultipleOptionsListDialogFragment().show(supportFragmentManager, "multiple options list dialog")
        }
        findViewById<Button>(R.id.buttonDialog4).setOnClickListener {
            CustomDialogFragment().show(supportFragmentManager, "custom dialog")
        }
        findViewById<Button>(R.id.buttonDialog5).setOnClickListener {
            PassParameterToActivityDialogFragment().show(supportFragmentManager, "pass parameter to activity dialog")
        }
    }

    override fun onPositiveButtonClick(dialog: DialogFragment) {
        Log.d("main-activity", "Positive button from dialog clicked")
    }

    override fun onNegativeButtonClick(dialog: DialogFragment) {
        Log.d("main-activity", "Negative button from dialog clicked")
    }
}