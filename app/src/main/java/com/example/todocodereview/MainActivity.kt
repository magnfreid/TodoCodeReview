package com.example.todocodereview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todocodereview.database.Todo
import com.example.todocodereview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TodoViewModel
    private lateinit var adapter: TodoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TodoViewModel()::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val clearButton = binding.btnClear
        val recyclerView = binding.rvTodo

        adapter = TodoAdapter(mutableListOf(), { todoId, isChecked ->
            viewModel.updateTodoCheckbox(todoId, isChecked)

        }) {
            openEditDialog(it)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnAdd.setOnClickListener {
            val name = binding.etNewTodo.text.toString()
            if (name.isNotBlank() && name.isNotEmpty()) {
                val newTodo = Todo(name = name)
                viewModel.insertTodo(newTodo)
                binding.etNewTodo.text?.clear()
            }
        }


        viewModel.todos.observe(this) {
            adapter.update(it.toMutableList())
        }

        viewModel.checkedTodos.observe(this) { checkedTodos ->
            if (checkedTodos.isNotEmpty()) {
                clearButton.isEnabled = true
                clearButton.setOnClickListener {
                    viewModel.deleteCheckedTodos(checkedTodos)
                }
            } else {
                clearButton.isEnabled = false
            }
        }
    }

    private fun openEditDialog(todo: Todo) {
        val editDialogFragment = EditDialogFragment(todo) {
            viewModel.updateTodoText(todo.id, it)
        }
        editDialogFragment.show(supportFragmentManager, null)
    }
}
