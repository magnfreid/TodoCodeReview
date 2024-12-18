package com.example.todocodereview

import kotlinx.coroutines.flow.Flow

class TodoRepository {
    private val todoDao = TodoDatabase.getInstance().todoDao()

    val todos: Flow<List<Todo>> = todoDao.getAll()
    val checkedTodos: Flow<List<Todo>> = todoDao.getChecked(true)

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun deleteChecked(todos: List<Todo>) {
        todoDao.deleteChecked(todos)
    }

    suspend fun updateCheckBox(todoID: Int, isChecked: Boolean) {
        todoDao.updateCheckbox(todoID, isChecked)
    }
}