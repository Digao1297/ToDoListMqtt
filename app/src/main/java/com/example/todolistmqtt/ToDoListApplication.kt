package com.example.todolistmqtt

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.todolistmqtt.drivers.worker.TaskSyncWorker
import com.example.todolistmqtt.domain.repository.CommunicationRepository
import com.example.todolistmqtt.domain.repository.TaskRepository
import com.example.todolistmqtt.domain.usecase.mqtt.TaskSyncSenderUseCase
import com.example.todolistmqtt.domain.usecase.task.MarkTaskAsSyncedUseCase
import com.example.todolistmqtt.domain.usecase.task.PendingSyncTasksUseCase
import dagger.assisted.Assisted
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ToDoListApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var taskSyncWorkerFactory: TaskSyncWorkerFactory


    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(taskSyncWorkerFactory)
            .build()

}

class TaskSyncWorkerFactory @Inject constructor(
    private val getPendingSyncTasksUseCase: PendingSyncTasksUseCase,
    private val taskSyncSenderUseCase: TaskSyncSenderUseCase,
    ) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker =
        TaskSyncWorker(
            getPendingSyncTasksUseCase = getPendingSyncTasksUseCase,
            taskSyncSenderUseCase = taskSyncSenderUseCase,
            context = appContext,
            params = workerParameters,
        )

}