package com.example.todolistmqtt.drivers.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.TaskProto
import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.drivers.mqtt.MQTTConstants
import com.example.todolistmqtt.domain.repository.CommunicationRepository
import com.example.todolistmqtt.domain.repository.TaskRepository
import com.example.todolistmqtt.domain.usecase.mqtt.TaskSyncSenderUseCase
import com.example.todolistmqtt.domain.usecase.task.MarkTaskAsSyncedUseCase
import com.example.todolistmqtt.domain.usecase.task.PendingSyncTasksUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.withContext

@HiltWorker
class TaskSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    @Assisted private val getPendingSyncTasksUseCase: PendingSyncTasksUseCase,
    @Assisted private val taskSyncSenderUseCase: TaskSyncSenderUseCase,
) : CoroutineWorker(context, params) {

    companion object {
        private val TAG = this::class.simpleName
    }

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                getPendingSyncTasksUseCase().collect{state ->
                    when(state){
                        is ResultStatus.Error -> {
                            val data = Data.Builder()
                                .putString("Error getPendingSyncTasksUseCase",state.throwable.stackTraceToString())
                                .build()
                            Result.failure(data)
                        }
                        ResultStatus.Loading -> {}
                        is ResultStatus.Success -> {
                            taskSyncSenderUseCase(state.data)
                        }
                    }
                }

                Result.success()
            } catch (e: Exception) {
                Log.d(TAG, e.stackTraceToString())
                val data = Data.Builder()
                    .putString("Error No worker",e.stackTraceToString())
                    .build()
                Result.failure(data)
            }
        }
    }
}