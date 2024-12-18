package com.example.todocodereview

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun deleteChecked(todos: List<Todo>)

    @Query("UPDATE todos SET isChecked = :isChecked WHERE id = :todoId")
    suspend fun updateCheckbox(todoId:Int, isChecked: Boolean)

    @Query("SELECT * FROM todos")
    fun getAll(): Flow<List<Todo>>

    @Query("SELECT * FROM todos WHERE isChecked = :isChecked")
    fun getChecked(isChecked: Boolean): Flow<List<Todo>>
}