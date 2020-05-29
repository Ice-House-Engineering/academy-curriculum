package com.example.hellodatapersistence7

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class CurrencyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "currency", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table currency (" +
                "id primary key," +
                "name varchar(200)," +
                "cryptocurrency int default 0)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p2==2 && p1==1) {
            p0?.execSQL("alter table currency add column new_column int default 7")
        }
    }
}