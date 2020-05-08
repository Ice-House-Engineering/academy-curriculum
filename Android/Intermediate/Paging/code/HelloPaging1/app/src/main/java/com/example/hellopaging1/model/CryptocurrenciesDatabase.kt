package com.example.hellopaging1.model

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
                            cryptocurrencies.forEach {
                                val cryptocurrency = CryptocurrencyModel(id=0, name=it)
                                database.cryptocurrenciesDao().insertCryptocurrency(cryptocurrency)
                            }
                        }
                    }
                })
                .build()
        }
    }
}

val cryptocurrencies = arrayOf("BITCOIN", "ETHEREUM", "XRP", "TETHER", "BITCOIN CASH", "BITCOIN SV", "LITECOIN", "EOS", "BINANCE COIN", "TEZOS", "UNUS SED LEO",
"STELLAR", "CHAINLINK", "CARDANO", "TRON", "HUOBI TOKEN", "MONERO", "DASH", "USD COIN", "ETHEREUM CLASSIC", "CRYPTO.COM COIN", "HEDGETRADE", "NEO", "COSMOS",
"IOTA", "NEM", "ZCASH", "MAKER", "OKB", "ONTOLOGY", "FTX TOKEN", "PAXOS STANDARD", "DOGECOIN", "BASIC ATTENTION TOKEN", "BINANCE USD", "VECHAIN", "TRUEUSD",
"BITCOIN GOLD", "DECRED", "LISK", "HEDERA HASHGRAPH", "QTUM", "ZB TOKEN", "ICON", "ALGORAND", "0X", "AUGUR", "KUCOIN SHARES", "WAVES", "BITCOIN DIAMOND",
"KYBER NETWORK", "RAVENCOIN", "MULTI-COLLATERAL DAI", "SYNTHETIX NETWORK TOKEN", "MONACOIN", "OMISEGO", "STEEM", "MCO", "DXCHAIN TOKEN", "THETA", "ENJIN COIN",
"NEXO", "NANO", "BYTOM", "SIACOIN", "HOLO", "ABBC COIN", "DIGIXDAO", "NERVOS NETWORK", "HORIZEN", "DIGIBYTE", "BITSHARES", "V.SYSTEMS", "BITTORRENT", "HYPERCASH",
"STATUS", "KOMODO", "BYTECOIN", "TERRA", "ZILLIQA", "IOST", "VERGE", "SWIPE", "WAX", "ENERGI", "SEELE", "GOLEM", "ARDOR", "REN", "STASIS EURO", "MOLECULAR FUTURE",
"BLOCKSTACK", "ZCOIN", "AELF", "MATIC NETWORK", "YAP STONE", "AETERNITY", "NUMERAIRE", "LOOPRING", "AAVE")