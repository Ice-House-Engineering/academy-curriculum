package com.example.hellodatapersistence8

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [CryptoCurrency::class, Programmer::class], version = 1)
abstract class CryptoCurrencyDatabase : RoomDatabase() {

    abstract fun cryptocurrenciesDao(): CryptoCurrencyDao

    abstract fun programmersDao(): ProgrammerDao
}