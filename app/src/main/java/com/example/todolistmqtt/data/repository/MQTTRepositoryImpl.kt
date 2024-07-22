package com.example.todolistmqtt.data.repository

import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.data.remote.mqtt.MQTTClient
import com.example.todolistmqtt.data.remote.mqtt.MQTTState
import com.example.todolistmqtt.data.remote.mqtt.MQTTStateFlow
import com.example.todolistmqtt.domain.repository.CommunicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MQTTRepositoryImpl
@Inject constructor(
    private val client: MQTTClient,
    private val mqttStateFlow: MQTTStateFlow,
) : CommunicationRepository {
    override suspend fun connect(): Flow<ResultStatus<Unit>> = flow<ResultStatus<Unit>> {
        emit(ResultStatus.Loading)
        client.connect()
        mqttStateFlow.state.collect { state ->
            when (state) {
                is MQTTState.Error -> emit(ResultStatus.Error(state.throwable))
                MQTTState.Success -> emit(ResultStatus.Success<Unit>(Unit))
            }
        }
    }.catch { throwable ->
        emit(ResultStatus.Error(throwable))
    }

    override suspend fun disconnect(): Flow<ResultStatus<Unit>> = flow<ResultStatus<Unit>> {
        emit(ResultStatus.Loading)
        client.disconnect()
        mqttStateFlow.state.collect { state ->
            when (state) {
                is MQTTState.Error -> emit(ResultStatus.Error(state.throwable))
                MQTTState.Success -> emit(ResultStatus.Success<Unit>(Unit))
            }
        }
    }.catch { throwable ->
        emit(ResultStatus.Error(throwable))
    }

    override suspend fun publish(topic: String, payload: ByteArray): Flow<ResultStatus<Unit>> =
        flow<ResultStatus<Unit>> {
            emit(ResultStatus.Loading)
            client.publish(topic = topic, payload = payload)
            mqttStateFlow.state.collect { state ->
                when (state) {
                    is MQTTState.Error -> emit(ResultStatus.Error(state.throwable))
                    MQTTState.Success -> emit(ResultStatus.Success<Unit>(Unit))
                }
            }
        }.catch { throwable ->
            emit(ResultStatus.Error(throwable))
        }

    override suspend fun subscribe(topic: String): Flow<ResultStatus<Unit>> =
        flow<ResultStatus<Unit>> {
            emit(ResultStatus.Loading)
            client.subscribe(topic = topic)
            mqttStateFlow.state.collect { state ->
                when (state) {
                    is MQTTState.Error -> emit(ResultStatus.Error(state.throwable))
                    MQTTState.Success -> emit(ResultStatus.Success<Unit>(Unit))
                }
            }
        }.catch { throwable ->
            emit(ResultStatus.Error(throwable))
        }

    override suspend fun unsubscribe(topic: String): Flow<ResultStatus<Unit>> =
        flow<ResultStatus<Unit>> {
            emit(ResultStatus.Loading)
            client.unsubscribe(topic = topic)
            mqttStateFlow.state.collect { state ->
                when (state) {
                    is MQTTState.Error -> emit(ResultStatus.Error(state.throwable))
                    MQTTState.Success -> emit(ResultStatus.Success<Unit>(Unit))
                }
            }
        }.catch { throwable ->
            emit(ResultStatus.Error(throwable))
        }
}