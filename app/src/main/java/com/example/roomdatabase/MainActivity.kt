package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel

    var checked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val database: Database? = Database.getInstance(this@MainActivity)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding.switchButton.setOnClickListener {
            if (binding.switchButton.isChecked) {
                checked = true
                Toast.makeText(
                    this, checked.toString(), Toast.LENGTH_SHORT
                ).show()
            } else {
                checked = false
                Toast.makeText(this, checked.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        binding.insert.setOnClickListener {
            val name: String = binding.etName.text.toString()
            val rollNo: String = binding.etRollNo.text.toString()
            if (!name.isEmpty() || !rollNo.isEmpty() ) {
                database!!.dao().insertStudentDetails(Student(name, rollNo.toInt(), checked))
                listData()
            }else Toast.makeText(this, "input the student details!", Toast.LENGTH_SHORT).show()

        }
        listData()


    }

    fun listData() {
        binding.rv.layoutManager = LinearLayoutManager(this)
        mainActivityViewModel.getStudents(this).observe(this, Observer {
            val rvAdapter = RVAdapter(
                it, this, mainActivityViewModel, this@MainActivity
            )
            binding.rv.adapter = rvAdapter
        })
    }

}