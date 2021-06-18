package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.Todo
import io.reactivex.Flowable
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(
    private val storage: TodoStorage
) {
    fun observeAll(): Flowable<List<Todo>> =
        storage.observeAll()
}