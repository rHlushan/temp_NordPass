package com.nordpass.task.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    private val _error = SingleLiveEvent<ErrorMessage>()
    val error: LiveData<ErrorMessage> get() = _error

    protected fun Disposable.attach() {
        disposables.add(this)
    }

    protected open fun handleError(error: Throwable) {
        this._error.value = ErrorMessageMapper.map(error)
    }

    override fun onCleared() {
        disposables.clear()
    }
}