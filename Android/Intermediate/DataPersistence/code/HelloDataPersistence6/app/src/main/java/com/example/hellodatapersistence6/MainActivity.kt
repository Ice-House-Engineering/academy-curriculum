package com.example.hellodatapersistence6

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val file = File(applicationContext.filesDir, "file.sqlite3")
        val sqlitedb = SQLiteDatabase.openOrCreateDatabase(file, null)
        sqlitedb.execSQL("create table currency (" +
                "id primary key," +
                "name varchar(200)," +
                "cryptocurrency int default 0)")

        val row1 = ContentValues()
        row1.put("name", "bitcoin")
        row1.put("cryptocurrency", 1)
        sqlitedb.insert("currency", null, row1)
    }
}
