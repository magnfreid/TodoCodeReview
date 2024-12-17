package com.example.todocodereview


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TodoViewModel(context: Context) : ViewModel() {

    private val database = DatabaseHandler(context).database

    val todos: LiveData<MutableList<Todo>> get() = database.todoDao().getAll()

    val checkedTodos: LiveData<MutableList<Todo>> get() = database.todoDao().getChecked(true)

    fun insertTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            database.todoDao().insert(todo)
        }
    }

    fun deleteCheckedTodos(todos: List<Todo>) {
        viewModelScope.launch(Dispatchers.IO) {
            database.todoDao().deleteChecked(todos)
        }
    }

    fun updateTodoCheckbox(todoId: Int, isChecked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            database.todoDao().updateCheckbox(todoId, isChecked)
        }
    }

}

class TodoViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
