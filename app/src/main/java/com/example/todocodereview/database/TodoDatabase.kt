package com.example.todocodereview.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todocodereview.App

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var _instance: TodoDatabase? = null
        fun getInstance(): TodoDatabase {
            return _instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    App.instance.applicationContext,
                    TodoDatabase::class.java,
                    "todos-database"
                ).fallbackToDestructiveMigration()
                    .build()
                _instance = instance
                return instance
            }
        }
    }
}

