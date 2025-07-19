package com.vharya.aktifitas7_2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DatabaseUserAdapter(context: Context) {
    private var databaseAdapter: DatabaseAdapter = DatabaseAdapter(context)

    companion object {
        private const val TABLE_NAME = "users"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    fun register(username: String?, password: String?): Long {
        val database = databaseAdapter.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_USERNAME, username)
        values.put(COLUMN_PASSWORD, password)

        return database.insert(TABLE_NAME, null, values)
    }

    fun login(username: String?, password: String?): Boolean {
        val database = databaseAdapter.readableDatabase

        try {
            val cursor = database.rawQuery(
                "SELECT * FROM $TABLE_NAME WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?",
                arrayOf( username, password )
            )

            val result = cursor.moveToFirst()
            return result
        } catch (e: Exception) {
            Log.e("ERROR DB", "${e.message}")
            Log.e("ERROR DB", "${e.stackTrace}")
        }
        return false
    }
}