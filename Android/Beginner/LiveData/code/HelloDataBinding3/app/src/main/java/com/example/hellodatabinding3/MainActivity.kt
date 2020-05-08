package com.example.hellodatabinding3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.hellodatabinding3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.myData = MyData(ObservableField("jakarta"), ObservableField("indonesia"), ObservableInt(56))
        binding.activity = this
    }

    fun onButtonClicked(view: View) {
        binding.myData!!.city.set("singapore")
        binding.myData!!.people.set(1 + binding.myData!!.people.get())
    }
}
