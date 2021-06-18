package com.nordpass.tt.storage.todo

import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.common.Io
import com.nordpass.tt.usecase.todolist.TodoStorage
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

internal class RoomTodoStorage @Inject constructor(
    private val dao: TodoDao,
    @Io private val scheduler: Scheduler
) : TodoStorage {

    override fun save(todoList: List<Todo>): Completable =
        Single.fromCallable { todoList.map { item -> item.toDbEntity() } }
            .flatMapCompletable { dbEntities -> dao.save(dbEntities) }
            .subscribeOn(scheduler)

    override fun observeAll(): Flowable<List<Todo>> =
        dao.observeAll()
            .map { dbEntities -> dbEntities.map { db -> db.toEntity() } }
            .subscribeOn(scheduler)

    override fun observeById(id: Long): Flowable<Todo> =
        dao.observeById(id)
            .map { db -> db.toEntity() }
            .subscribeOn(scheduler)

    override fun update(todo: Todo): Completable =
        Single.fromCallable { todo.toDbEntity() }
            .flatMapCompletable { dbEntity -> dao.update(dbEntity) }
            .subscribeOn(scheduler)
}