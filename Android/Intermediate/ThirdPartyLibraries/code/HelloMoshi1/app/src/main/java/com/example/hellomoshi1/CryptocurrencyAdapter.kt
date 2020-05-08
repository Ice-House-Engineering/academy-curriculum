package com.example.hellomoshi1

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson


class CryptocurrencyAdapter {

    @ToJson
    fun toJson(cryptocurrency: Cryptocurrency) : String {
        return cryptocurrency.name
    }

    @FromJson
    fun fromJson(json: String) : Cryptocurrency {
        return Cryptocurrency(json, 0, null, null, created=null)
    }
}