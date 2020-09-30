package com.example.mynotes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.media.projection.MediaProjection

class DbManager{
    val dbName="MyNotes"
    val dbTable="Notes"
    val colID="ID"
    val colTitle="Title"
    val colDes="Description"
    val dbVersion=1

    val sqlcreatetable="CREATE TABLE IF NOT EXISTS $dbTable ($colID INTEGER PRIMARY KEY, $colTitle TEXT, $colDes TEXT);"

    var sqlDB:SQLiteDatabase?=null

    constructor(context: Context){
        var db=DatabaseHelperNotes(context)
        sqlDB=db.writableDatabase
    }

    inner class DatabaseHelperNotes:SQLiteOpenHelper{
        constructor(context:Context):super(context,dbName,null,dbVersion){

        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlcreatetable)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

            db!!.execSQL("DROP TABLE IF EXISTS $dbTable")
        }
    }

    fun Insert(value:ContentValues):Long{
        val ID=sqlDB!!.insert(dbTable,"",value)
        return ID
    }

    fun Query(projection: Array<String>?, selection:String, selectionArgs:Array<String>, SortOrder:String): Cursor {
        val qb=SQLiteQueryBuilder()
        qb.tables=dbTable
        val cursor=qb.query(sqlDB,projection,selection,selectionArgs,null,null,SortOrder)
        return cursor
    }

    fun Delete(selection: String,selectionArgs: Array<String>):Int{
        val count= sqlDB!!.delete(dbTable,selection,selectionArgs)
        return count
    }

    fun Update(value:ContentValues,selection:String,selectionArgs:Array<String>):Int{
        val count=sqlDB!!.update(dbTable,value,selection,selectionArgs)
        return count
    }

}