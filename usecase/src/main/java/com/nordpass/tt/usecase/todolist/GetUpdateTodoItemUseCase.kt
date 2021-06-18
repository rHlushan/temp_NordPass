package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.Todo
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class GetUpdateTodoItemUseCase @Inject constructor(
    private val storage: TodoStorage
) {

    fun observeById(id: Long): Flowable<Todo> =
        storage.observeById(id)

    fun update(todo: Todo): Completable =
        storage.update(todo)
}