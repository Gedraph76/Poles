package com.example.poles

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY " +
                "AUTOINCREMENT,NAMEPAT TEXT,GENDER TEXT,AGEPAT TEXT,LOCATIONPAT TEXT," +
                "IDNUMBER TEXT,PHONEPAT TEXT, EMAILPAT TEXT , PASSWORD TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun insertData(namePat: String, gender: String, agePat: String, locationPat:String,
                   idNumber: String, phonePat: String, emailPat: String, passwordPat:String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, namePat)
        contentValues.put(COL_2, gender)
        contentValues.put(COL_3, agePat)
        contentValues.put(COL_4, locationPat)
        contentValues.put(COL_5, idNumber)
        contentValues.put(COL_6, phonePat)
        contentValues.put(COL_7, emailPat)
        contentValues.put(COL_8, passwordPat)
        db.insert(TABLE_NAME, null, contentValues)
    }


    fun updateData( namePat: String, gender: String, agePat: String, locationPat:String,
                   idNumber: String, phonePat: String, emailpat: String, passwordPat:String):
            Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, namePat)
        contentValues.put(COL_2, gender)
        contentValues.put(COL_3, agePat)
        contentValues.put(COL_4, locationPat)
        contentValues.put(COL_5, idNumber)
        contentValues.put(COL_6, phonePat)
        contentValues.put(COL_7, emailpat)
        contentValues.put(COL_8, passwordPat)
        db.update(TABLE_NAME ,contentValues, "ID = ?", arrayOf(namePat))
        db.close()
        return true
    }


    fun deleteData(name:String){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"NAME"+"=?", arrayOf(name))
        return db.close()
    }


    val allData : Cursor
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM " + "Pats", null)
            return res
        }


    companion object {
        val DATABASE_NAME = "mydb"
        val TABLE_NAME = "Pats"
        val COL_1 = "ID"
        val COL_2 = "NAMEPAT"
        val COL_3 = "GENDER"
        val COL_4 = "AGEPAT"
        val COL_5 = "LOCATIONPAT"
        val COL_6 = "IDNUMBER"
        val COL_7 = "PHONEPAT"
        val COL_8 = "EMAILPAT"
        val COL_9 = "PASSWORDPAT"
    }
}