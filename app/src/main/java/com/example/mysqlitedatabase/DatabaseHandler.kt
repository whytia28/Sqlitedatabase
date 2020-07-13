package com.example.mysqlitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "student_db"
        val DB_VERSION = 1
        val TABLE_NAME = "StudentTable"
        val COLUMN_ID = "id"
        val COLUMN_NAME = "name"
        val COLUMN_EMAIL = "email"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_STUDENT_TABLE = ("CREATE TABLE $TABLE_NAME(" +
                "$COLUMN_ID INTEGER PRIMARY KEY," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_EMAIL TEXT)")
        p0?.execSQL(CREATE_STUDENT_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    fun addStudent(student: Student): Long {
        //mendapatkan object database
        val db = this.writableDatabase

        //data yang akan dimasukkan ke db dibungkus dahulu dengan content value
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, student.id)
        contentValues.put(COLUMN_NAME, student.name)
        contentValues.put(COLUMN_EMAIL, student.email)

        //melakukan insert ke db
        val isSuccess = db.insert(TABLE_NAME, null, contentValues)
        db.close()

        //menutup db
        return isSuccess
    }

    fun viewAllStudents(): ArrayList<Student> {
        //list penampung daftar student
        val studentList = ArrayList<Student>()

        //query slecet
        val selectQuery = "SELECT * FROM $TABLE_NAME"

        val db = this.readableDatabase

        // Membuat cursor
        var cursor: Cursor?

        //mengisi cursor dengan data dari table student
        cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val idStudent = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val nameStudent = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val emailStudent = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))

                //buat object student
                val objectStudent = Student(idStudent, nameStudent, emailStudent)

                //menambahkan object ke list
                studentList.add(objectStudent)
            } while (cursor.moveToNext()) //dilakukan sampe baris terakhir
        }
        return studentList
    }

    fun updateStudent(student: Student): Int {

        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, student.id)
        contentValues.put(COLUMN_NAME, student.name) // Nama Student
        contentValues.put(COLUMN_EMAIL, student.email) // Email Student

        //update baris
        val isSuccess = db.update(TABLE_NAME, contentValues, "$COLUMN_ID= ${student.id}", null)
        db.close()
        return isSuccess
    }

    fun deleteStudent(student: Student): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, student.id)

        //menghapus baris
        val isSuccess = db.delete(TABLE_NAME, "$COLUMN_ID= ${student.id}", null)
        db.close()

        return isSuccess
    }
}