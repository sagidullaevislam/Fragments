package com.example.demoroomapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentsDao {
    @Query("SELECT * FROM student")
    suspend fun getListOfStudents(): List<StudentData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudent(student: StudentData)

    @Query("SELECT * FROM student")
    suspend fun getStudentById(id: Int): StudentData

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStudent(student: StudentData)

    @Delete
    suspend fun deleteStudent(student: StudentData)

    @Query("SELECT * FROM student WHERE name LIKE '%' || :name || '%'")
    suspend fun searchStudentByName(name: String): List<StudentData>
}