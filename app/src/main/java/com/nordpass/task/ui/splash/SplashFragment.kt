package com.nordpass.task.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nordpass.task.R
import com.nordpass.task.databinding.FragmentSplashBinding
import com.nordpass.task.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.completed.observe(viewLifecycleOwner, { showTodoList() })
        viewModel.error.observe(viewLifecycleOwner, ::showError)
        val binding = FragmentSplashBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun showTodoList() =
        findNavController().navigate(SplashFragmentDirections.actionTodoList())
}