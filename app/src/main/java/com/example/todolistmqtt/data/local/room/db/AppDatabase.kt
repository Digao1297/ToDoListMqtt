package com.example.todolistmqtt.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistmqtt.data.local.room.dao.TaskDao
import com.example.todolistmqtt.data.local.room.entity.TaskEntity

@Database(
    entities = [
        TaskEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}