package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.Todo
import io.reactivex.Single
import javax.inject.Inject

class GetTodoItemUseCase @Inject constructor(
    private val storage: TodoStorage
) {
    fun get(id: Int): Single<Todo> {
        return storage.getById(id)
    }
}