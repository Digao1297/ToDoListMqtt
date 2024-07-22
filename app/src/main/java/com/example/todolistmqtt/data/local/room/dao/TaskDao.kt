package com.example.todolistmqtt.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolistmqtt.data.local.room.db.DbConstants
import com.example.todolistmqtt.data.local.room.entity.TaskEntity
import com.example.todolistmqtt.domain.model.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun create(task: TaskEntity)

    @Update
    suspend fun update(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)

    @Query("SELECT * FROM ${DbConstants.TASK_TABLE_NAME} WHERE ${DbConstants.TASK_COLUMN_ID} = :id")
    suspend fun getTaskBy(id: Long): TaskEntity

    @Query("SELECT * FROM ${DbConstants.TASK_TABLE_NAME}")
    suspend fun getTasks(): List<TaskEntity>

    @Query("SELECT * FROM ${DbConstants.TASK_TABLE_NAME} WHERE ${DbConstants.TASK_COLUMN_IS_PENDING_SYNC} = 1")
    suspend fun getPendingSyncTasks(): List<TaskEntity>

    @Query("UPDATE ${DbConstants.TASK_TABLE_NAME} SET ${DbConstants.TASK_COLUMN_IS_PENDING_SYNC} = 0 WHERE id = :taskId")
    suspend fun markTaskAsSynced(taskId: Int)
}