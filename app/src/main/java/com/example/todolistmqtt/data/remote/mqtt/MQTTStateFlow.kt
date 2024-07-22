package com.example.todolistmqtt.data.remote.mqtt

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


class MQTTStateFlow {
    private val _state = MutableSharedFlow<MQTTState>()
    val state = _state.asSharedFlow()

    suspend fun emitState(state: MQTTState) {
        _state.emit(state)
    }
}

sealed class MQTTState {
    object Success : MQTTState()
    data class Error(val throwable: Throwable) : MQTTState()
}