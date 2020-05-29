package com.example.hellodatapersistence8

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "programmers",
        foreignKeys=[ForeignKey(entity=CryptoCurrency::class, parentColumns = ["id"], childColumns = ["cryptocurrency_id"], onDelete=ForeignKey.CASCADE)])
data class Programmer(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var name: String,

    var cryptocurrency_id: Int
)