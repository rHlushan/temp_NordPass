package com.nordpass.tt.api.backend.todo

import com.nordpass.tt.usecase.Todo
import org.threeten.bp.Instant
import org.threeten.bp.format.DateTimeFormatter

internal class TodoResponse {
    var id: Long? = null
    var title: String? = null
    var completed: Boolean? = null
    var createdAt: String? = null // example 2021-01-13T03:50:03.794+05:30
    var userId: Long? = null
    var updatedAt: String? = null
}

internal fun TodoResponse.toEntityOrNull(): Todo? {
    val id = this.id ?: return null
    val title = this.title ?: return null

    return Todo(
        id = id,
        title = title,
        isCompleted = (this.completed ?: false),
        createdAt = (this.createdAt ?: ""),
        updatedAt = this.updatedAt?.let { value ->
            Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(value))
        },
        userId = this.userId
    )
}