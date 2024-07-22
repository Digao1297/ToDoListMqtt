package com.example.todolistmqtt.domain.usecase.communication

import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.domain.repository.CommunicationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SubscribeUseCase {
    suspend operator fun invoke(topic: String): Flow<ResultStatus<Unit>>
}

class SubscribeUseCaseImpl @Inject constructor(
    private val repository: CommunicationRepository,
) : SubscribeUseCase {
    override suspend fun invoke(topic: String): Flow<ResultStatus<Unit>> =
        repository.subscribe(topic = topic)

}