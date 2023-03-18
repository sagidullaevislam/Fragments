package com.example.demoroomapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StudentData::class], version = 2)
abstract class StudentDataBase : RoomDatabase() {


    abstract fun getStudentDao(): StudentsDao


    companion object {
        const val DATABASE_NAME = "db_name"

        fun getInstance(context: Context): StudentDataBase {
            return Room.databaseBuilder(
                context, StudentDataBase::class.java, StudentDataBase.DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}