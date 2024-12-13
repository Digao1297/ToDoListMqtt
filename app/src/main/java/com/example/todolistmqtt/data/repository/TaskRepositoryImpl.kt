package com.example.todolistmqtt.data.repository

import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.data.TaskDataSource
import com.example.todolistmqtt.data.local.room.entity.toModel
import com.example.todolistmqtt.domain.model.Task
import com.example.todolistmqtt.domain.model.toEntity
import com.example.todolistmqtt.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dataSource: TaskDataSource,
) : TaskRepository {
    override suspend fun create(task: Task): Flow<ResultStatus<Unit>> = flow<ResultStatus<Unit>> {
        emit(ResultStatus.Loading)
        dataSource.create(task.toEntity())
        emit(ResultStatus.Success(Unit))
    }.catch { throwable ->
        emit(ResultStatus.Error(throwable))
    }

    override suspend fun read(): Flow<ResultStatus<List<Task>>> = flow<ResultStatus<List<Task>>> {
        emit(ResultStatus.Loading)
        val entities = dataSource.getTasks()
        val models = entities.map { it.toModel() }
        emit(ResultStatus.Success(models))
    }.catch { throwable ->
        emit(ResultStatus.Error(throwable))
    }

    override suspend fun getPendingSyncTasks(): Flow<ResultStatus<List<Task>>> = flow<ResultStatus<List<Task>>> {
        emit(ResultStatus.Loading)
        val tasks =  dataSource.getPendingSyncTasks().map { it.toModel() }
        emit(ResultStatus.Success(tasks))
    }.catch { throwable ->
        emit(ResultStatus.Error(throwable))
    }

    override suspend fun markTaskAsSynced(taskId: Int): Flow<ResultStatus<Unit>> = flow<ResultStatus<Unit>> {
        emit(ResultStatus.Loading)
        dataSource.markTaskAsSynced(taskId)
        emit(ResultStatus.Success(Unit))
    }.catch { throwable ->
        emit(ResultStatus.Error(throwable))
    }
}