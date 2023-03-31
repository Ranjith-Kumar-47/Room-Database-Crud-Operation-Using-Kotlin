package com.example.roomdatabase

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

const val DATABASE_NAME = "studentDB"
private var instance: Database? = null
@androidx.room.Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class Database() : RoomDatabase() {

    //    fun getInstance(context: Context):Database{
//
//    }
    companion object {
        fun getInstance(context: Context):Database?{
            if (instance==null)
            {
                instance = Room.databaseBuilder(context,Database::class.java, DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return instance
        }
    }
    abstract fun dao():DAO

}