package com.example.hellomvvm4.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hellomvvm4.model.Database
import java.util.*


class BasicAndroidViewModel(application: Application) : AndroidViewModel(application) {

    private val cryptocurrency : MutableLiveData<String> = MutableLiveData(Database.getCryptocurrencies())
    private var app : Application = application

    fun setCryptocurrencies(newCryptocurrencies : String) {
        Database.setCryptocurrencies(newCryptocurrencies.toUpperCase(Locale.getDefault()))
        cryptocurrency.value = Database.getCryptocurrencies()
        createToast()
    }

    fun getCryptocurrencies(): LiveData<String> = cryptocurrency

    private fun createToast() {
        val text = "Updating Cryptocurrency"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(app, text, duration)
        toast.show()
    }
}