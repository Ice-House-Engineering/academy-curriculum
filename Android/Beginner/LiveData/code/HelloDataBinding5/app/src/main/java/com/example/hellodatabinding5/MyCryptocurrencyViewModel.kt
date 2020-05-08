package com.example.hellodatabinding5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel


class MyCryptocurrencyViewModel : ViewModel() {

    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val fullname: LiveData<String> = Transformations.map(name) {
            name -> "Full $name"
    }

    val rank: LiveData<String> = Transformations.switchMap(name) { cryptocurrencyName -> getRank(cryptocurrencyName) }

    fun getRank(cryptocurrencyName: String) : LiveData<String> {
        val rank = MutableLiveData<String>()
        rank.value = "$cryptocurrencyName is First"
        return rank
    }
}