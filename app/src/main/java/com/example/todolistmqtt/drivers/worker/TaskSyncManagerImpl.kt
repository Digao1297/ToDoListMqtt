package com.example.todolistmqtt.drivers.worker

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

    override fun startSyncManager() {
        val taskSyncWorkRequest =
            PeriodicWorkRequestBuilder<TaskSyncWorker>(15, TimeUnit.MINUTES)
                .build()
        workManager.enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            taskSyncWorkRequest,
        )
    }


    override fun stopSyncManager() {
        workManager.cancelUniqueWork(WORK_NAME)
    }
}