package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.Todo
import io.reactivex.Completable
import io.reactivex.Single

interface TodoStorage {
    fun save(todoList: List<Todo>): Completable

    fun getAll(): Single<List<Todo>>

    fun getById(id: Int): Single<Todo>

    fun removeById(ids: List<Int>): Completable
}