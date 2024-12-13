package com.example.todolistmqtt.domain.usecase.mqtt

import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.data.model.TaskData
import com.example.todolistmqtt.data.model.toModel
import com.example.todolistmqtt.domain.usecase.task.CreateTaskUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TaskSyncReceiverUseCase{
    suspend operator fun invoke(taskData: TaskData): Flow<ResultStatus<Unit>>
}

class TaskSyncReceiverUseCaseImpl @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase,
):TaskSyncReceiverUseCase{
    override suspend fun invoke(taskData: TaskData): Flow<ResultStatus<Unit>> =
        createTaskUseCase.invoke(taskData.toModel())
}