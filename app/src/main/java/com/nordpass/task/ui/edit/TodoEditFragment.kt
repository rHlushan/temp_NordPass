package com.nordpass.task.ui.edit

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputLayout
import com.nordpass.task.R
import com.nordpass.task.ui.base.BaseFragment
import com.nordpass.task.ui.details.TodoDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoEditFragment : BaseFragment(R.layout.fragment_todo_edit) {

    private val viewModel: TodoDetailsViewModel by viewModels()
    private val args: TodoEditFragmentArgs by navArgs()

    private var textInputLayout: TextInputLayout? = null
    private var saveButton: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.init(args.todoId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textInputLayout = view.findViewById<TextInputLayout>(R.id.fragment_todo_edit_input)
        saveButton = view.findViewById<View>(R.id.fragment_todo_edit_save)

        viewModel.item.observe(this.viewLifecycleOwner, { item ->
            textInputLayout?.editText?.setText(item.title)
        })

        saveButton?.setOnClickListener { handleUpdateTitle() }
    }

    private fun handleUpdateTitle() {
        val text = textInputLayout?.editText?.text?.toString()
        if (text.isNullOrBlank()) {
            textInputLayout?.error = getString(R.string.title_empty_error)
        } else {
            viewModel.updateTitle(newTitle = text)
        }
    }

    override fun onDestroyView() {
        textInputLayout = null
        saveButton = null

        super.onDestroyView()
    }
}