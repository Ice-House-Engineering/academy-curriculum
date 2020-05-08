package com.example.hellodatapersistence7

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbhelper = CurrencyDatabaseHelper(this)
        val sqlitedb = dbhelper.writableDatabase

        val row1 = ContentValues()
        row1.put("name", "bitcoin")
        row1.put("cryptocurrency", 1)
        sqlitedb.insert("currency", null, row1)
    }
}
