package com.example.hellomoshi1

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Fork(
    val name: String,
    val price: Int
)