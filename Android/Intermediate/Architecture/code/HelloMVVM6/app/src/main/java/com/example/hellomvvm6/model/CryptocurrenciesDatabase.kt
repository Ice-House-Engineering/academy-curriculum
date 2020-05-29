package com.example.hellomvvm6.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlin.concurrent.thread


// Adapted from https://github.com/android/sunflower/blob/master/app/src/main/java/com/google/samples/apps/sunflower/data/AppDatabase.kt
@Database(entities = [CryptocurrencyModel::class], version = 1, exportSchema = false)
abstract class CryptocurrenciesDatabase : RoomDatabase() {

    abstract fun cryptocurrenciesDao(): CryptocurrenciesDao

    companion object {

        @Volatile private var instance: CryptocurrenciesDatabase? = null

        fun getInstance(context: Context): CryptocurrenciesDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CryptocurrenciesDatabase {
            return Room.databaseBuilder(context, CryptocurrenciesDatabase::class.java, "cryptocurrencies_db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        thread {
                            val database = getInstance(context)
                            val cryptocurrency1 =
                                CryptocurrencyModel(id = 0, name = "BITCOIN", title = "bitcoin.org")
                            database.cryptocurrenciesDao().insertCryptocurrency(cryptocurrency1)
                            val cryptocurrency2 = CryptocurrencyModel(
                                id = 0,
                                name = "ETHEREUM",
                                title = "ethereum.org"
                            )
                            database.cryptocurrenciesDao().insertCryptocurrency(cryptocurrency2)
                            val cryptocurrency3 = CryptocurrencyModel(
                                id = 0,
                                name = "BITCOIN CASH",
                                title = "bitcoin.com"
                            )
                            database.cryptocurrenciesDao().insertCryptocurrency(cryptocurrency3)
                            val cryptocurrency4 = CryptocurrencyModel(
                                id = 0,
                                name = "LITECOIN",
                                title = "litecoin.org"
                            )
                            database.cryptocurrenciesDao().insertCryptocurrency(cryptocurrency4)
                        }
                    }
                })
                .build()
        }
    }
}