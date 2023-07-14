package com.example.loginsignupdemo.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.loginsignupdemo.data.UserData

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, "
                + "$COLUMN_FIRSTNAME TEXT,$COLUMN_LASTNAME TEXT,$COLUMN_EMAIL TEXT, $COLUMN_PASSWORD TEXT)")
        db?.execSQL(createTableQuery)
    }
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val TABLE_NAME = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRSTNAME = "firstName"
        private const val COLUMN_LASTNAME = "lastName"
        private const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }

    fun addUser(fname:String,lname:String,mail:String,pass:String):Long{
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FIRSTNAME,fname)
            put(COLUMN_LASTNAME, lname)
            put(COLUMN_EMAIL, mail)
            put(COLUMN_PASSWORD, pass)
        }
        return db.insert(TABLE_NAME,null,values)

    }

    @SuppressLint("Range", "Recycle")
    fun getUserthroughMail(email: String): UserData? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ?"
        val cursor = db.rawQuery(query, arrayOf(email))

        //db.insert(TABLE_NAME,null,)

        return if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            val password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
            UserData(
                id,
                firstName = cursor.getColumnIndex(COLUMN_FIRSTNAME).toString(),
                lastName = cursor.getColumnIndex(COLUMN_LASTNAME).toString(),
                email,
                password
            )
        } else {
            null
        }
    }

        fun getCursor(mail:String):Cursor?{
            val db = this.readableDatabase
            val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ?"
            return db.rawQuery(query, arrayOf(mail))
        }
    }


