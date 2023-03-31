package com.example.roomdatabase

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val mutableLiveData: MutableLiveData<ArrayList<Student>> = MutableLiveData()
    fun getStudents(context: Context): LiveData<ArrayList<Student>> {
        val studentDetails = Database.getInstance(context)!!.dao().getStudentDetails() as ArrayList
//        val finalData: ArrayList<String> = ArrayList()
//        studentDetails.forEach {
//            val pass: Boolean
//            finalData.add("${it.id}: Name: ${it.name}, Roll No: ${it.rollNo}, Pass: ${it.pass}")
//        }
        studentDetails.reverse()
        mutableLiveData.postValue(studentDetails)
        return mutableLiveData
    }
    fun deleteStudent(context: Context,student: Student){
        Database.getInstance(context)!!.dao().deleteStudentDetails(student)
    }
    fun updateStudent(context: Context,student: Student){
        Database.getInstance(context)!!.dao().updateStudentDetails(student)
    }
}