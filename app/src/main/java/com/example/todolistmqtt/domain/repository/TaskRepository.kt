package com.example.todolistmqtt.domain.repository

import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun create(task: Task): Flow<ResultStatus<Unit>>

    suspend fun read(): Flow<ResultStatus<List<Task>>>

    suspend fun getPendingSyncTasks(): Flow<ResultStatus<List<Task>>>

    suspend fun markTaskAsSynced(taskId: Int):Flow<ResultStatus<Unit>>
}