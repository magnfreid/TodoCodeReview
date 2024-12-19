package com.example.todocodereview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todocodereview.database.Todo
import com.example.todocodereview.database.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TodoViewModel : ViewModel() {

    private val todoRepository = TodoRepository()

    val todos: LiveData<List<Todo>> get() = todoRepository.todos.asLiveData()

    val checkedTodos: LiveData<List<Todo>> get() = todoRepository.checkedTodos.asLiveData()


    fun insertTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insert(todo)
        }
    }

    fun deleteCheckedTodos(todos: List<Todo>) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteChecked(todos)
        }
    }

    fun updateTodoCheckbox(todoId: Int, isChecked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateCheckBox(todoId, isChecked)
        }
    }

    fun updateTodoText(todoId: Int, newText: String) {
        viewModelScope.launch {
            todoRepository.updateTodoText(todoId, newText)
        }
    }
}