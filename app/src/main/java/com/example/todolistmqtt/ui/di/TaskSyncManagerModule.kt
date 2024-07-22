package com.example.todolistmqtt.ui.di

import android.content.Context
import com.example.todolistmqtt.ui.worker.TaskSyncManagerImpl
import com.example.todolistmqtt.ui.worker.TaskSyncManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TaskSyncManagerModule {

    @Provides
    fun provideTaskSyncManager(@ApplicationContext context: Context): TaskSyncManager {
        return TaskSyncManagerImpl(context)
    }
}