package com.example.todolistmqtt.ui.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class TaskSyncManagerImpl(
    private val context: Context
) : TaskSyncManager {

    companion object {
        private const val WORK_NAME = "TaskSyncWork"
    }

    private val workManager = WorkManager.getInstance(context)

    override fun startPeriodicSync() {
        val taskSyncWorkRequest =
            PeriodicWorkRequestBuilder<TaskSyncWorker>(15, TimeUnit.MINUTES)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()
        workManager.enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            taskSyncWorkRequest,
        )
    }

    override fun stopPeriodicSync() {
        workManager.cancelUniqueWork(WORK_NAME)
    }
}