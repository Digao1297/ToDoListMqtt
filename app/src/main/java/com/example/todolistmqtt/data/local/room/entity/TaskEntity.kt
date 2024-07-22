package com.example.todolistmqtt.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolistmqtt.data.local.room.db.DbConstants
import com.example.todolistmqtt.domain.model.Task
import javax.annotation.Nonnull

@Entity(tableName = DbConstants.TASK_TABLE_NAME)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @Nonnull
    @ColumnInfo(DbConstants.TASK_COLUMN_ID)
    val id: Int = 0,

    @ColumnInfo(DbConstants.TASK_COLUMN_VALUE)
    val value: String,

    @ColumnInfo(DbConstants.TASK_COLUMN_TIMESTAMP)
    val timestamp: Long,

    @ColumnInfo(DbConstants.TASK_COLUMN_IS_PENDING_SYNC)
    val isPendingSync: Boolean,

    )


fun TaskEntity.toModel(): Task =
    Task(id, value, timestamp, isPendingSync)

