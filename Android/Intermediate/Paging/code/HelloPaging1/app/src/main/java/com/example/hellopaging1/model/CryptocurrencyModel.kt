package com.example.hellopaging1.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cryptocurrencies")
data class CryptocurrencyModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var name: String

)