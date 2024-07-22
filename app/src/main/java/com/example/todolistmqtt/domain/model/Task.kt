package com.example.todolistmqtt.domain.model

import com.example.todolistmqtt.data.local.room.entity.TaskEntity

data class Task(
    val id: Int? = null,
    val value: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isPendingSync: Boolean = true,
)

fun Task.toEntity(): TaskEntity {
    return TaskEntity(id ?: 0, value, timestamp, isPendingSync)
}