package com.example.hellopaging1.model

import androidx.paging.DataSource
import androidx.room.*


@Dao
interface CryptocurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCryptocurrency(cryptocurrency: CryptocurrencyModel)

    @Query("select * from cryptocurrencies order by id asc")
    fun getAllCryptocurrencies(): DataSource.Factory<Int, CryptocurrencyModel>

}