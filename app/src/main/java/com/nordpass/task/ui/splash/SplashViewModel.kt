package com.nordpass.task.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.tt.usecase.common.UI
import com.nordpass.tt.usecase.todolist.SyncTodoUseCase
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy

class SplashViewModel @ViewModelInject constructor(
    syncTodoUseCase: SyncTodoUseCase,
    @UI private val uiScheduler: Scheduler
) : BaseViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _completed = MutableLiveData<Unit>()
    val completed: LiveData<Unit> get() = _completed

    init {
        syncTodoUseCase.sync()
            .observeOn(uiScheduler)
            .doOnSubscribe { _isLoading.value = true }
            .doFinally { _isLoading.value = false }
            .subscribeBy(onComplete = { _completed.setValue(Unit) }, onError = ::handleError)
            .attach()
    }
}