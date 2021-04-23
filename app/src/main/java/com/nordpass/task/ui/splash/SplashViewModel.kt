package com.nordpass.task.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.tt.usecase.todolist.SyncTodoUseCase
import io.reactivex.rxkotlin.subscribeBy

class SplashViewModel @ViewModelInject constructor(
    syncTodoUseCase: SyncTodoUseCase
) : BaseViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val completed = MutableLiveData<Unit>()

    init {
        syncTodoUseCase.sync()
            .doOnSubscribe { isLoading.postValue(true) }
            .doOnComplete { isLoading.postValue(false) }
            .doOnError { isLoading.postValue(false) }
            .subscribeBy(onComplete = { completed.postValue(Unit) }, onError = ::handleError)
            .attach()
    }
}