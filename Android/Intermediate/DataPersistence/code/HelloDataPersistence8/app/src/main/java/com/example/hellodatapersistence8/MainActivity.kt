package com.example.hellodatapersistence8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import kotlin.concurrent.thread


const val LOG = "android-room"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread {
            playingWithDatabase()
        }
    }

    fun playingWithDatabase() {
        val database: CryptoCurrencyDatabase = Room.databaseBuilder(this, CryptoCurrencyDatabase::class.java, "cryptocurrency_database")
            .build()
        val cryptoCurrencyDao = database.cryptocurrenciesDao()
        val programmerDao = database.programmersDao()

        programmerDao.deleteAllProgrammers()
        cryptoCurrencyDao.deleteAllCryptoCurrencies()

        val cryptoCurrency1 = CryptoCurrency(id=1, name="Ethereum", fiat_money=FiatMoney(total_supply=1000, usd_value=130), description=null)
        val cryptoCurrency2 = CryptoCurrency(id=0, name="Bitcoin", fiat_money=FiatMoney(total_supply=1000, usd_value=1000), description="The first cryptocurrency")

        cryptoCurrencyDao.insertCryptoCurrency(cryptoCurrency1)
        cryptoCurrencyDao.insertCryptoCurrency(cryptoCurrency2)

        val cryptoCurrencies = cryptoCurrencyDao.getAllCryptoCurrencies()

        for (cryptoCurrency in cryptoCurrencies) {
            Log.d(LOG, cryptoCurrency.name)
            Log.d(LOG, cryptoCurrency.id.toString())
            if (!cryptoCurrency.description.isNullOrBlank())
                Log.d(LOG, cryptoCurrency.description!!)
        }

        val programmer1 = Programmer(id=1, name="Jack Bauer", cryptocurrency_id = cryptoCurrencies[0].id)
        val programmer2 = Programmer(id=2, name="Wonder Woman", cryptocurrency_id = cryptoCurrencies[1].id)

        programmerDao.insertProgrammer(programmer1)
        programmerDao.insertProgrammer(programmer2)

        val programmers = programmerDao.searchProgrammer("Wonder Woman")

        for (programmer in programmers) {
            Log.d(LOG, programmer.name)
        }

        programmerDao.deleteProgrammers(programmer1, programmer2)
        cryptoCurrencyDao.deleteCryptoCurrencies(cryptoCurrency1, cryptoCurrency2)
    }
}
