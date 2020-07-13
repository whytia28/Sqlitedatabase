package com.example.mysqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        //buat object Databasehandler
        val dbHandler = DatabaseHandler(this)

        //menerima object student dari main activity
        val student = intent.getParcelableExtra<Student>("student")

        supportActionBar?.title ="Ubah data ${student?.name}"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //set edittext berdasarkan intent
        et_name.setText(student?.name)
        et_email.setText(student?.email)

        btn_save.setOnClickListener {
            //mengubah nama dan email yang ada pada object student yang diterima oleh intent
            student?.name = et_name.text.toString()
            student?.email = et_email.text.toString()

            student?.let {

                //melakukan update ke SQLite
                if (dbHandler.updateStudent(it) != 0) {
                    Toast.makeText(this, "Berhasil diubah", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Gagal diubah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}