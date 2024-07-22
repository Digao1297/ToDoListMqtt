package com.example.todolistmqtt.data.di

import com.example.todolistmqtt.data.TaskDataSource
import com.example.todolistmqtt.data.local.room.LocalTaskDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindLocalTaskDataSource(dataSource: LocalTaskDataSource): TaskDataSource
}