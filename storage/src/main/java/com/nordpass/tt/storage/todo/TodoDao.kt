package com.nordpass.tt.storage.todo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
internal interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(list: List<TodoEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(todo: TodoEntity): Completable

    @Query(
        """
        SELECT * FROM ${TodoEntity.TABLE_NAME} 
        ORDER BY ${TodoEntity.IS_COMPLETED_COLUMN_NAME} DESC,
        ${TodoEntity.UPDATED_AT_COLUMN_NAME} DESC
        """
    )
    fun observeAll(): Flowable<List<TodoEntity>>

    @Query("SELECT * FROM ${TodoEntity.TABLE_NAME} WHERE ${TodoEntity.ID_COLUMN_NAME} == :id")
    fun observeById(id: Long): Flowable<TodoEntity>
}