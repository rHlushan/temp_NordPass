package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.Todo
import io.reactivex.Single
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(
    private val storage: TodoStorage
) {
    fun get(): Single<List<Todo>> {
        return storage.getAll()
    }
}