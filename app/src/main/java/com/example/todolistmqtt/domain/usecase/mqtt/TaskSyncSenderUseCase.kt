package com.example.todolistmqtt.domain.usecase.mqtt

import com.example.TaskListProto
import com.example.TaskProto
import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.domain.model.Task
import com.example.todolistmqtt.domain.repository.CommunicationRepository
import com.example.todolistmqtt.domain.usecase.task.MarkTaskAsSyncedUseCase
import com.example.todolistmqtt.drivers.mqtt.MQTTConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface TaskSyncSenderUseCase {
    suspend operator fun invoke(tasks: List<Task>): Flow<ResultStatus<Unit>>
}

class TaskSyncSenderUseCaseImpl @Inject constructor(
    private val repository: CommunicationRepository,
    private val markTaskAsSyncedUseCase: MarkTaskAsSyncedUseCase,
) : TaskSyncSenderUseCase {
    override suspend fun invoke(tasks: List<Task>): Flow<ResultStatus<Unit>> {

        var protoTasks: List<TaskProto> = tasks.map { task ->
            TaskProto.newBuilder()
                .setId(task.id!!)
                .setValue(task.value)
                .setIsPendingSync(task.isPendingSync)
                .setTimestamp(task.timestamp)
                .build()
        }

        val taskProtoList = TaskListProto
            .newBuilder()
            .addAllTasks(protoTasks)
            .build()

        tasks.forEach{task ->
            markTaskAsSyncedUseCase(task.id!!)
        }

        return repository.publish(
            MQTTConstants.TOPIC_SYNCHRONIZED,
            taskProtoList.toByteArray(),
        )
    }
}