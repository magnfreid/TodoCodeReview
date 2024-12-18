package com.example.todocodereview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

class TodoAdapter(private val todos: MutableList<Todo>, val onCheckboxClick: (Int, Boolean) -> Unit) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: MaterialTextView = itemView.findViewById(R.id.tv_todo_item)
        val isCheckedView: CheckBox = itemView.findViewById(R.id.cb_todo_item)
        val container: ConstraintLayout = itemView.findViewById(R.id.todo_item_container)
        val primaryColor = ContextCompat.getColor(itemView.context, com.google.android.material.R.color.design_default_color_primary)
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
        val lightColor = ColorUtils.blendARGB(holder.primaryColor, Color.WHITE, 0.75f)
        val darkerColor = ColorUtils.blendARGB(holder.primaryColor, Color.WHITE, 0.65f)
        val color = if (position % 2 == 0) lightColor else darkerColor
        holder.container.setBackgroundColor(color)
        holder.nameTextView.text = currentTodo.name
        holder.isCheckedView.isChecked = currentTodo.isChecked
        holder.isCheckedView.setOnClickListener {
            currentTodo.isChecked = !currentTodo.isChecked
            onCheckboxClick(currentTodo.id, currentTodo.isChecked)
        }
    }

    fun update(newList:MutableList<Todo>) {
        todos.clear()
        todos.addAll(newList)
        notifyDataSetChanged()
    }
}