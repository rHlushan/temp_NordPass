package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.Todo
import io.reactivex.Completable
import io.reactivex.Flowable

interface TodoStorage {

    fun save(todoList: List<Todo>): Completable

    fun observeAll(): Flowable<List<Todo>>

    fun observeById(id: Long): Flowable<Todo>

    fun update(todo: Todo): Completable
}