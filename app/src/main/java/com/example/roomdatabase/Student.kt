package com.example.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

const val TABLE_NAME = "student"
@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "rollNo")
    var rollNo:Int,
    @ColumnInfo(name = "pass")
    var pass:Boolean
) {
    @Ignore
    constructor(name: String,rollNo: Int,pass: Boolean) : this(0,name,rollNo,pass)
}