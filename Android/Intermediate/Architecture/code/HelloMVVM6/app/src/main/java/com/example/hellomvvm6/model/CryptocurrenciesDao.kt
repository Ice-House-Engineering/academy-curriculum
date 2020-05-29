package com.example.hellomvvm6.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CryptocurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCryptocurrency(cryptocurrency: CryptocurrencyModel)

    @Query("select * from cryptocurrencies")
    fun getAllCryptocurrencies(): LiveData<List<CryptocurrencyModel>>

    @Query("select * from cryptocurrencies where name = :name")
    fun findCryptocurrency(name : String): LiveData<List<CryptocurrencyModel>>

    @Query("select 1 from cryptocurrencies where name = :name")
    fun cryptocurrencyExists(name : String): Boolean
}