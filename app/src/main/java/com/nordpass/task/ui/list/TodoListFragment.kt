package com.nordpass.task.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nordpass.task.R
import com.nordpass.task.ui.base.BaseFragment
import com.nordpass.tt.usecase.Todo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : BaseFragment(R.layout.fragment_list) {

    private val viewModel: TodoListViewModel by viewModels()

    private val adapter: TodoListAdapter = TodoListAdapter(::showTodoDetails)

    private var recycler: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.todoRecycler)

        recycler?.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, adapter::submitList)
    }

    override fun onDestroyView() {
        recycler?.adapter = null
        recycler = null

        super.onDestroyView()
    }

    private fun showTodoDetails(todo: Todo) =
        findNavController().navigate(TodoListFragmentDirections.actionTodoDetails(todo.id))
}