package com.example.todolistmqtt.domain.usecase.mqtt

import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.domain.repository.CommunicationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ConnectUseCase {
    suspend operator fun invoke(): Flow<ResultStatus<Unit>>
}

class ConnectUseCaseImpl @Inject constructor(
    private val repository: CommunicationRepository,
) : ConnectUseCase {
    override suspend fun invoke(): Flow<ResultStatus<Unit>> =
        repository.connect()
}