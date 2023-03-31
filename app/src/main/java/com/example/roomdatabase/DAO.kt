package com.example.roomdatabase

import androidx.room.*

@Dao
interface DAO {
    @Insert
    fun insertStudentDetails(student: Student)

    @Delete
    fun deleteStudentDetails(student: Student)

    @Update
    fun updateStudentDetails(student: Student)

    @Query("select * from $TABLE_NAME")
    fun getStudentDetails():List<Student>
}