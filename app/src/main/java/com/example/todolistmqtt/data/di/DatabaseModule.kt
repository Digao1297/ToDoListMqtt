package com.example.todolistmqtt.data.di

import android.content.Context
import androidx.room.Room
import com.example.todolistmqtt.data.local.room.dao.TaskDao
import com.example.todolistmqtt.data.local.room.db.AppDatabase
import com.example.todolistmqtt.data.local.room.db.DbConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DbConstants.APP_DATABASE_NAME,
        ).build()

    @Provides
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao = appDatabase.taskDao()
}