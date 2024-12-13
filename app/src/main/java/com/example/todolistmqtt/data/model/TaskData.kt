package com.example.todolistmqtt.data.model

import com.example.TaskProto
import com.example.todolistmqtt.domain.model.Task

data class TaskData(
    val id: Int,
    val value: String,
    val timestamp: Long,
    val isPendingSync: Boolean
)

fun TaskData.toModel() = Task(
    id = id,
    value = value,
    timestamp = timestamp,
    isPendingSync = isPendingSync,
)

fun TaskProto.toData() = TaskData(
    id = this.id,
    value = this.value,
    timestamp = this.timestamp,
    isPendingSync = true
)
