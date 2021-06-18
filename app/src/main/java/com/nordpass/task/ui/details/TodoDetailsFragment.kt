package com.nordpass.task.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nordpass.task.R
import com.nordpass.task.databinding.FragmentDetailsBinding
import com.nordpass.task.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoDetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val viewModel: TodoDetailsViewModel by viewModels()
    private val args: TodoDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.init(args.todoId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)
        binding.fragmentDetailsEdit.setOnClickListener { navigateToEdit() }
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun navigateToEdit() =
        findNavController().navigate(TodoDetailsFragmentDirections.actionTodoEdit(args.todoId))
}