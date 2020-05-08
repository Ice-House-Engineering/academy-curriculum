package com.example.hellodatapersistence8

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


data class FiatMoney(
    var usd_value: Int?,
    var total_supply: Int?
)

@Entity(tableName = "cryptocurrencies")
data class CryptoCurrency(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "cryptocurrency_name")
    var name: String,

    var description: String?,

    @Embedded
    var fiat_money: FiatMoney
)