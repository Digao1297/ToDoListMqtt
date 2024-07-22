package com.example.todolistmqtt.data

import com.example.todolistmqtt.data.local.room.entity.TaskEntity

interface TaskDataSource {
    suspend fun create(task: TaskEntity)

    suspend fun update(task: TaskEntity)

    suspend fun delete(task: TaskEntity)

    suspend fun getTaskBy(id: Long): TaskEntity

    suspend fun getTasks(): List<TaskEntity>

    suspend fun getPendingSyncTasks(): List<TaskEntity>

    suspend fun markTaskAsSynced(taskId: Int)
}