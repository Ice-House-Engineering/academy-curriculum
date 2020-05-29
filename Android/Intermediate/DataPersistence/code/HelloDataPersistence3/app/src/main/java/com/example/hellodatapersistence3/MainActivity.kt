package com.example.hellodatapersistence3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.Transient
import kotlinx.serialization.json.Json


const val LOG = "serialization-data"

@Serializable
class Currency {
    var id = 0

    @SerialName("displayName")
    var name = "currency"

    @Transient
    var cryptocurrency = true

    override fun toString() = "id: $id, name: $name, cryptocurrency: $cryptocurrency"
}

@Serializer(forClass=Currency::class)
object ExtCurrencySerializer

class MainActivity : AppCompatActivity() {

    @UseExperimental(kotlinx.serialization.UnstableDefault::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val currencySerializer = Currency.serializer()
        val currency = Currency()
        currency.cryptocurrency = false
        val string = Json.stringify(currencySerializer, currency)
        Log.d(LOG, string)

        val currency2 = Json.parse(ExtCurrencySerializer, string)
        Log.d(LOG, currency2.toString())
    }
}
