package com.example.hellodatapersistence5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase


const val LOG = "android-sqlite"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sqlitedb = SQLiteDatabase.create(null)
        sqlitedb.execSQL("create table currency (" +
                "id primary key," +
                "name varchar(200)," +
                "cryptocurrency int default 0)")

        val row1 = ContentValues()
        row1.put("name", "bitcoin")
        row1.put("cryptocurrency", 1)
        sqlitedb.insert("currency", null, row1)

        val row2 = ContentValues()
        row2.put("name", "usd")
        sqlitedb.insert("currency", null, row2)

        val cursor = sqlitedb.query("currency", arrayOf("id", "name", "cryptocurrency"),
            "name = ?", arrayOf("bitcoin"), null, null, null, null)
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val cryptocurrency = cursor.getInt(cursor.getColumnIndex("cryptocurrency"))
                Log.d(LOG, "$id, $name, $cryptocurrency")
                cursor.moveToNext()
            }
        }

        cursor.close()

        val updatedRow = ContentValues()
        updatedRow.put("name", "ethereum")
        sqlitedb.update("currency", updatedRow, "name = ?", arrayOf("bitcoin"))

        sqlitedb.execSQL("drop table currency")
    }
}
