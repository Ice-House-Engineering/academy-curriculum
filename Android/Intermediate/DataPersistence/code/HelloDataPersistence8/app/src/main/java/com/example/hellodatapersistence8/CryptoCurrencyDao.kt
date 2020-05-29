package com.example.hellodatapersistence8

import androidx.room.*


@Dao
interface CryptoCurrencyDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCryptoCurrency(cryptocurrency: CryptoCurrency)

    @Delete
    fun deleteCryptoCurrencies(vararg cryptocurrency: CryptoCurrency)

    @Query("delete from cryptocurrencies")
    fun deleteAllCryptoCurrencies()

    @Query("select * from cryptocurrencies")
    fun getAllCryptoCurrencies(): List<CryptoCurrency>
}