package com.example.todolistmqtt

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.todolistmqtt.ui.worker.TaskSyncWorker
import com.example.todolistmqtt.domain.repository.CommunicationRepository
import com.example.todolistmqtt.domain.repository.TaskRepository
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


    override fun onCreate() {
        super.onCreate()


    }
}

class TaskSyncWorkerFactory @Inject constructor(
    private val taskRepository: TaskRepository,
    private val communicationRepository: CommunicationRepository,
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? =
        TaskSyncWorker(
            taskRepository = taskRepository,
            communicationRepository = communicationRepository,
            context = appContext,
            params = workerParameters,
        )

}