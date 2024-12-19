package com.example.todocodereview.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var isChecked: Boolean = false
)
