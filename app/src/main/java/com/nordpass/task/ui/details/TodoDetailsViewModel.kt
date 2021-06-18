package com.nordpass.task.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.common.UI
import com.nordpass.tt.usecase.todolist.GetUpdateTodoItemUseCase
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import org.threeten.bp.Instant

class TodoDetailsViewModel @ViewModelInject constructor(
    private val getTodoItemUseCase: GetUpdateTodoItemUseCase,
    @UI private val uiScheduler: Scheduler
) : BaseViewModel() {

    private var disposable: Disposable? = null

    private val _item = MutableLiveData<Todo>()
    val item: LiveData<Todo> get() = _item

    //todo: better to use assisted inject
    fun init(id: Long) {
        unsubscribe()
        val newDisposable = getTodoItemUseCase.observeById(id)
            .observeOn(uiScheduler)
            .subscribeBy(onNext = _item::setValue, onError = ::handleError)
        newDisposable.attach()

        disposable = newDisposable
    }

    fun revertCompleted() {
        val itemValue = _item.value
        if (itemValue != null) {
            val updated = itemValue.copy(isCompleted = !itemValue.isCompleted)
            updateItem(updated)
        }
    }

    fun updateTitle(newTitle: String) {
        val itemValue = _item.value
        if (itemValue != null) {
            val updated = itemValue.copy(title = newTitle)
            updateItem(updated)
        }
    }

    private fun updateItem(updated: Todo) {
        val updatedWithTimestamp = updated.copy(updatedAt = Instant.now())
        getTodoItemUseCase.update(updatedWithTimestamp)
            .observeOn(uiScheduler)
            .subscribeBy(onError = ::handleError)
            .attach()
    }

    private fun unsubscribe() {
        disposable?.dispose()
        disposable = null
    }
}