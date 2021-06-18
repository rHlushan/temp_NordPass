package com.nordpass.tt.storage.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nordpass.tt.usecase.Todo
import org.threeten.bp.Instant

@Entity(tableName = TodoEntity.TABLE_NAME)
internal class TodoEntity(
    @ColumnInfo(name = ID_COLUMN_NAME)
    @PrimaryKey val id: Long,

    @ColumnInfo(name = TITLE_COLUMN_NAME)
    val title: String,

    @ColumnInfo(name = IS_COMPLETED_COLUMN_NAME)
    val isCompleted: Boolean,

    @ColumnInfo(name = CREATED_AT_COLUMN_NAME)
    val createdAt: String,

    @ColumnInfo(name = UPDATED_AT_COLUMN_NAME)
    val updatedAt: Long?,

    @ColumnInfo(name = USER_ID_COLUMN_NAME)
    val userId: Long?
) {
    companion object {
        const val TABLE_NAME = "todo"

        const val ID_COLUMN_NAME = "id"
        const val TITLE_COLUMN_NAME = "title"
        const val IS_COMPLETED_COLUMN_NAME = "isCompleted"
        const val CREATED_AT_COLUMN_NAME = "createdAt"
        const val UPDATED_AT_COLUMN_NAME = "updatedAt"
        const val USER_ID_COLUMN_NAME = "userId"
    }
}

internal fun TodoEntity.toEntity(): Todo =
    Todo(
        id = this.id,
        title = this.title,
        isCompleted = this.isCompleted,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt?.let(Instant::ofEpochMilli),//todo better to use converter
        userId = this.userId
    )

internal fun Todo.toDbEntity(): TodoEntity =
    TodoEntity(
        id = this.id,
        title = this.title,
        isCompleted = this.isCompleted,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt?.toEpochMilli(),
        userId = this.userId
    )