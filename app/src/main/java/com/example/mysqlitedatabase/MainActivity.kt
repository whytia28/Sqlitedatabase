package com.example.mysqlitedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fab_add.setOnClickListener {
            val intentActivity = Intent(this, AddActivity::class.java)
            startActivity(intentActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData() {
        val dbHandler = DatabaseHandler(this)
        val listStudent = arrayListOf<Student>()

        listStudent.addAll(dbHandler.viewAllStudents())

        val adapter = StudentAdapter(listStudent)
        rv_student.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_student.adapter = adapter
    }
}