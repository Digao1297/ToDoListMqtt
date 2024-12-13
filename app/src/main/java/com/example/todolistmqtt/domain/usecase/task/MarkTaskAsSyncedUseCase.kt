package com.example.todolistmqtt.domain.usecase.task

import com.example.todolistmqtt.domain.repository.TaskRepository
import javax.inject.Inject

interface MarkTaskAsSyncedUseCase {
    suspend operator fun invoke(id: Int)
}

class MarkTaskAsSyncedUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
): MarkTaskAsSyncedUseCase{

    override suspend fun invoke(id: Int) {
        repository.markTaskAsSynced(id)
    }
}