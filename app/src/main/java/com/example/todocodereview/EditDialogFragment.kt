package com.example.todocodereview


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.todocodereview.database.Todo
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class EditDialogFragment(val todo: Todo, val onSave: (String) -> Unit) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_edit, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val saveButton: MaterialButton = view.findViewById(R.id.btn_edit_dialog_save)
        val nameEditText: EditText = view.findViewById(R.id.et_edit_dialog_name)
        Log.i("SOUT", todo.name)
        nameEditText.setText(todo.name)
        Log.i("SOUT", "${nameEditText.text}")
        saveButton.setOnClickListener {
            onSave(nameEditText.text.toString())
            dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }

}