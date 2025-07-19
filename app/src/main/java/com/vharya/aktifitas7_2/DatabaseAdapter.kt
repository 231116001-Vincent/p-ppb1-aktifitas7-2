package com.vharya.aktifitas7_2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseAdapter (context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db == null) return

        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db == null) return

        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "database"
        private const val DATABASE_VERSION = 1
        private const val DATABASE_CREATE = (
            "CREATE TABLE users (" +
                "id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL" +
            ");"
        )
    }
}