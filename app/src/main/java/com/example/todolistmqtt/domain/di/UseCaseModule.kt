package com.example.todolistmqtt.domain.di

import com.example.todolistmqtt.domain.usecase.mqtt.ConnectUseCase
import com.example.todolistmqtt.domain.usecase.mqtt.ConnectUseCaseImpl
import com.example.todolistmqtt.domain.usecase.mqtt.SubscribeUseCase
import com.example.todolistmqtt.domain.usecase.mqtt.SubscribeUseCaseImpl
import com.example.todolistmqtt.domain.usecase.mqtt.TaskSyncReceiverUseCase
import com.example.todolistmqtt.domain.usecase.mqtt.TaskSyncReceiverUseCaseImpl
import com.example.todolistmqtt.domain.usecase.mqtt.TaskSyncSenderUseCase
import com.example.todolistmqtt.domain.usecase.mqtt.TaskSyncSenderUseCaseImpl
import com.example.todolistmqtt.domain.usecase.task.CreateTaskUseCase
import com.example.todolistmqtt.domain.usecase.task.CreateTaskUseCaseImpl
import com.example.todolistmqtt.domain.usecase.task.MarkTaskAsSyncedUseCase
import com.example.todolistmqtt.domain.usecase.task.MarkTaskAsSyncedUseCaseImpl
import com.example.todolistmqtt.domain.usecase.task.PendingSyncTasksUseCase
import com.example.todolistmqtt.domain.usecase.task.PendingSyncTasksUseCaseImpl
import com.example.todolistmqtt.domain.usecase.task.ReadTasksUseCase
import com.example.todolistmqtt.domain.usecase.task.ReadTasksUseCaseImpl
import com.example.todolistmqtt.domain.usecase.ui.SyncStatusTaskUseCase
import com.example.todolistmqtt.domain.usecase.ui.SyncStatusTaskUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindCreateTaskUseCase(useCase: CreateTaskUseCaseImpl): CreateTaskUseCase

    @Binds
    fun bindReadTasksUseCase(useCase: ReadTasksUseCaseImpl): ReadTasksUseCase

    @Binds
    fun bindConnectUseCase(useCase: ConnectUseCaseImpl): ConnectUseCase

    @Binds
    fun bindSubscribeUseCase(useCase: SubscribeUseCaseImpl): SubscribeUseCase

    @Binds
    fun bindTaskSyncReceiverUseCase(useCase: TaskSyncReceiverUseCaseImpl): TaskSyncReceiverUseCase

    @Binds
    fun bindTaskSyncSenderUseCase(useCase: TaskSyncSenderUseCaseImpl): TaskSyncSenderUseCase

    @Binds
    fun bindSyncStatusTaskUseCase(useCase: SyncStatusTaskUseCaseImpl): SyncStatusTaskUseCase

    @Binds
    fun bindMarkTaskAsSyncedUseCase(useCase: MarkTaskAsSyncedUseCaseImpl): MarkTaskAsSyncedUseCase

    @Binds
    fun bindPendingSyncTasksUseCase(useCase: PendingSyncTasksUseCaseImpl): PendingSyncTasksUseCase

}