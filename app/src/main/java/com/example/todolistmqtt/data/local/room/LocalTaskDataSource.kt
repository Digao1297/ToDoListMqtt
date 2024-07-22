package com.example.todolistmqtt.data.local.room

import com.example.todolistmqtt.data.TaskDataSource
import com.example.todolistmqtt.data.local.room.dao.TaskDao
import com.example.todolistmqtt.data.local.room.entity.TaskEntity
import javax.inject.Inject

class LocalTaskDataSource @Inject constructor(
    private val dao: TaskDao
) : TaskDataSource {
    override suspend fun create(task: TaskEntity) {
        dao.create(task)
    }

    override suspend fun update(task: TaskEntity) {
        dao.update(task)
    }

    override suspend fun delete(task: TaskEntity) {
        dao.delete(task)
    }

    override suspend fun getTaskBy(id: Long): TaskEntity {
        return dao.getTaskBy(id)
    }

    override suspend fun getTasks(): List<TaskEntity> {
        return dao.getTasks()
    }

    override suspend fun getPendingSyncTasks(): List<TaskEntity> {
        return dao.getPendingSyncTasks()
    }

    override suspend fun markTaskAsSynced(taskId: Int) {
        dao.markTaskAsSynced(taskId)
    }
}