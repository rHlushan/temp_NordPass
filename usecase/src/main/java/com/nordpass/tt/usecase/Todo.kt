package com.nordpass.tt.usecase

import org.threeten.bp.Instant

data class Todo(
    val id: Long,
    val title: String,
    val isCompleted: Boolean,
    val createdAt: String,
    val updatedAt: Instant?,
    val userId: Long?
)