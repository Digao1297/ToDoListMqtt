package com.example.todolistmqtt.domain.usecase.task

import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.domain.model.Task
import com.example.todolistmqtt.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ReadTasksUseCase {
    suspend operator fun invoke(): Flow<ResultStatus<List<Task>>>
}

class ReadTasksUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
) : ReadTasksUseCase {
    override suspend operator fun invoke(): Flow<ResultStatus<List<Task>>> =
        repository.read()
}