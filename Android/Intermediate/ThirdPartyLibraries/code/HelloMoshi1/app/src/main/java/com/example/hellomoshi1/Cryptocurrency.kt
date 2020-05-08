package com.example.hellomoshi1

import com.squareup.moshi.JsonClass
import java.util.*


@JsonClass(generateAdapter = true)
data class Cryptocurrency(
    val name: String,
    val price: Int,
    val marketcap: Int?,
    val forks: List<Fork>?,
    val created: Date?
)