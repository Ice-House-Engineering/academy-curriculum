package com.example.hellodialog1

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import java.util.Calendar

const val logTag = "calendar"


class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calendar = Calendar.getInstance()
        Log.d(logTag, "calendar.time: " + calendar.time.toString())
        Log.d(logTag, "calendar.get(Calendar.YEAR): " + calendar.get(Calendar.YEAR).toString())
        Log.d(logTag, "calendar.get(Calendar.MONTH): " + calendar.get(Calendar.MONTH).toString())
        Log.d(logTag, "calendar.get(Calendar.DAY_OF_MONTH): " + calendar.get(Calendar.DAY_OF_MONTH).toString())
        Log.d(logTag, "calendar.get(Calendar.HOUR_OF_DAY): " + calendar.get(Calendar.HOUR_OF_DAY).toString())
        Log.d(logTag, "calendar.get(Calendar.MINUTE): " + calendar.get(Calendar.MINUTE).toString())
        Log.d(logTag, "calendar.timeZone.displayName: " + calendar.timeZone.displayName.toString())
        Log.d(logTag, "calendar.timeZone.rawOffset: " + calendar.timeZone.rawOffset.toString())
        Log.d(logTag, "calendar.timeInMillis: " + calendar.timeInMillis.toString())

        findViewById<Button>(R.id.buttonDateDialog).setOnClickListener {
            DatePickerDialog(this, this, 2019, 2, 2).show()
        }
        findViewById<Button>(R.id.buttonTimeDialog).setOnClickListener {
            TimePickerDialog(this, this, 23, 2, true).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.d(logTag, "$year - $month - $dayOfMonth")
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Log.d(logTag, "$hourOfDay - $minute")
    }
}
