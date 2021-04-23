package com.nordpass.task.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.tt.usecase.Todo

class TodoDetailsViewModel @ViewModelInject constructor(): BaseViewModel() {

    val item = MutableLiveData<Todo>()

    fun init(item: Todo) {
        this.item.value = item
    }

    fun onFinishedClicked() {
        //todo
    }

    fun onTodoClicked() {
        //todo
    }

    fun onEditClicked() {
        //todo
    }
}