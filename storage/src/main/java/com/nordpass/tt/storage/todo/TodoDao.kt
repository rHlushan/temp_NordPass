package com.nordpass.tt.storage.todo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
internal interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateOrCreate(list: List<TodoEntity>): Completable

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Single<List<TodoEntity>>

    @Query("SELECT * FROM $TABLE_NAME")
    fun observeAll(): Flowable<List<TodoEntity>>

    @Query("DELETE FROM $TABLE_NAME WHERE id in (:ids)")
    fun remove(ids: List<Int>): Completable

    @Query("SELECT * FROM $TABLE_NAME WHERE id == :id")
    fun getById(id: Int): Single<TodoEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE id == :id")
    fun observeById(id: Int): Flowable<TodoEntity>

    companion object {
        const val TABLE_NAME = "todo"
    }
}