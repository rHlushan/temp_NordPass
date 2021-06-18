package com.nordpass.tt.usecase.todolist

import io.reactivex.Completable
import javax.inject.Inject

class SyncTodoUseCase @Inject constructor(
    private val api: TodoListApi,
    private val storage: TodoStorage
) {
    fun sync(): Completable =
        api.get().flatMapCompletable(storage::save)
}