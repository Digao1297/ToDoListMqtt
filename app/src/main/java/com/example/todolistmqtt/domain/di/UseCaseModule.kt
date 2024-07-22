package com.example.todolistmqtt.domain.di

import com.example.todolistmqtt.domain.usecase.communication.ConnectUseCase
import com.example.todolistmqtt.domain.usecase.communication.ConnectUseCaseImpl
import com.example.todolistmqtt.domain.usecase.communication.SubscribeUseCase
import com.example.todolistmqtt.domain.usecase.communication.SubscribeUseCaseImpl
import com.example.todolistmqtt.domain.usecase.task.CreateTaskUseCase
import com.example.todolistmqtt.domain.usecase.task.CreateTaskUseCaseImpl
import com.example.todolistmqtt.domain.usecase.task.ReadTasksUseCase
import com.example.todolistmqtt.domain.usecase.task.ReadTasksUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindCreateTaskUseCase(useCase: CreateTaskUseCaseImpl): CreateTaskUseCase

    @Binds
    fun bindReadTasksUseCase(useCase: ReadTasksUseCaseImpl): ReadTasksUseCase

    @Binds
    fun bindConnectUseCase(useCase: ConnectUseCaseImpl): ConnectUseCase

    @Binds
    fun bindSubscribeUseCase(useCase: SubscribeUseCaseImpl): SubscribeUseCase

}