package com.example.todolistmqtt.domain.repository

import com.example.todolistmqtt.common.ResultStatus
import kotlinx.coroutines.flow.Flow

interface CommunicationRepository {
    suspend fun connect(): Flow<ResultStatus<Unit>>
    suspend fun disconnect(): Flow<ResultStatus<Unit>>
    suspend fun publish(topic: String, payload: ByteArray): Flow<ResultStatus<Unit>>
    suspend fun subscribe(topic: String): Flow<ResultStatus<Unit>>
    suspend fun unsubscribe(topic: String): Flow<ResultStatus<Unit>>
}