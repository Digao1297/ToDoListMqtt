package com.example.todolistmqtt.data.di

import com.example.todolistmqtt.data.repository.MQTTRepositoryImpl
import com.example.todolistmqtt.data.repository.TaskRepositoryImpl
import com.example.todolistmqtt.domain.repository.CommunicationRepository
import com.example.todolistmqtt.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindToDoListRepository(repository: MQTTRepositoryImpl): CommunicationRepository


    @Binds
    fun bindTaskRepository(repository: TaskRepositoryImpl): TaskRepository

}