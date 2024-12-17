package com.example.todocodereview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

class TodoAdapter(private val todos: MutableList<Todo>, val onCheckboxChanged: (Int, Boolean) -> Unit) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: MaterialTextView = itemView.findViewById(R.id.tv_todo_item)
        val isCheckedView: CheckBox = itemView.findViewById(R.id.cb_todo_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.nameTextView.text = currentTodo.name
        holder.isCheckedView.isChecked = currentTodo.isChecked
        holder.isCheckedView.setOnClickListener {
            currentTodo.isChecked = !currentTodo.isChecked
            onCheckboxChanged(currentTodo.id, currentTodo.isChecked)
        }
    }

    fun toggleTodoIsChecked(todo: Todo) {
        val index = todos.indexOf(todo)
        val currentTodo = todos[index]
        currentTodo.isChecked = !currentTodo.isChecked
        notifyItemChanged(index)
    }

    fun updateTodoName(todo: Todo, newName: String) {
        val index = todos.indexOf(todo)
        val currentTodo = todos[index]
        currentTodo.name = newName
        notifyItemChanged(index)
    }

    fun update(newList:MutableList<Todo>) {
        todos.clear()
        todos.addAll(newList)
        notifyDataSetChanged()
    }
}