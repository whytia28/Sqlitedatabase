package com.example.mysqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        supportActionBar?.title = "Tambah data student"

        val dbHandler = DatabaseHandler(this)

        btn_save.setOnClickListener {
            val student = Student(null, et_name.text.toString(), et_email.text.toString())

            if (dbHandler.addStudent(student) != 0.toLong()) {
                Toast.makeText(this, "Sukses menambahkan", Toast.LENGTH_LONG).show()
                finish()
            }else {
                Toast.makeText(this, "Gagal menambahkan data", Toast.LENGTH_LONG).show()
            }
        }
    }
}