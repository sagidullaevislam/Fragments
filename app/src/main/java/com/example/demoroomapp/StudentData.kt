package com.example.demoroomapp

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "student")
data class StudentData (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var surname: String,
)