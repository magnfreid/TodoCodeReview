package com.example.todocodereview

import android.content.Context
import androidx.room.Room

class DatabaseHandler(context: Context) {
    val database: TodoDatabase = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java, "todo-database"
    ).fallbackToDestructiveMigration().build()
}


