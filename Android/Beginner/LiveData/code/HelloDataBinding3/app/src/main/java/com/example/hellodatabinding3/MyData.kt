package com.example.hellodatabinding3

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt


data class MyData(
    val city : ObservableField<String>,
    val country : ObservableField<String>,
    val people : ObservableInt)