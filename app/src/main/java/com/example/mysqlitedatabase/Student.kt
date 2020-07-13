package com.example.mysqlitedatabase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student (val id: Int?, var name: String, var email: String) : Parcelable