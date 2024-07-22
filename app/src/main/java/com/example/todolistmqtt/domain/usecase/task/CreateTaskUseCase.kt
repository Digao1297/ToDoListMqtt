package com.example.todolistmqtt.domain.usecase.task

import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.domain.model.Task
import com.example.todolistmqtt.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CreateTaskUseCase {
    suspend operator fun invoke(task: Task): Flow<ResultStatus<Unit>>
}

class CreateTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
) : CreateTaskUseCase {
    override suspend operator fun invoke(task: Task): Flow<ResultStatus<Unit>> =
        repository.create(task)

}