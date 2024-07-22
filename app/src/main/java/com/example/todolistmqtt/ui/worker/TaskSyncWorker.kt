package com.example.todolistmqtt.ui.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.TaskProto
import com.example.todolistmqtt.data.remote.mqtt.MQTTConstants
import com.example.todolistmqtt.domain.repository.CommunicationRepository
import com.example.todolistmqtt.domain.repository.TaskRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class TaskSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    @Assisted private val taskRepository: TaskRepository,
    @Assisted private val communicationRepository: CommunicationRepository,
) : CoroutineWorker(context, params) {

    companion object {
        private val TAG = this::class.simpleName
    }

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val pendingTasks = taskRepository.getPendingSyncTasks()


                pendingTasks.forEach { task ->
                    val protoTask = TaskProto.newBuilder()
                        .setId(task.id!!)
                        .setValue(task.value)
                        .setIsPendingSync(task.isPendingSync)
                        .setTimestamp(task.timestamp)
                        .build()

                    communicationRepository.publish(
                        MQTTConstants.TOPIC_SYNCHRONIZED,
                        protoTask.toByteArray(),
                    )
                    taskRepository.markTaskAsSynced(task.id)
                }
                Result.success()
            } catch (e: Exception) {
                Log.d(TAG, e.stackTraceToString())
                Result.failure()
            }
        }
    }
}