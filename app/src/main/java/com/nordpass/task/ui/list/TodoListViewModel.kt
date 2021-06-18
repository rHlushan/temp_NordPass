package com.nordpass.task.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.task.ui.base.SingleLiveEvent
import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.common.UI
import com.nordpass.tt.usecase.todolist.GetTodoListUseCase
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy

class TodoListViewModel @ViewModelInject constructor(
    getTodoListUseCase: GetTodoListUseCase,
    @UI uiScheduler: Scheduler
) : BaseViewModel() {
    private val _items = MutableLiveData<List<Todo>>()
    val items: LiveData<List<Todo>> get() = _items

    init {
        getTodoListUseCase.observeAll()
            .observeOn(uiScheduler)
            .subscribeBy(onNext = _items::setValue, onError = ::handleError)
            .attach()
    }
}