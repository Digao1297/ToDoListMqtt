package com.example.todolistmqtt.domain.usecase.ui

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

interface SyncStatusTaskUseCase{
    operator fun invoke(): Flow<SyncStatus>
    suspend fun startSync()
    suspend fun onCompletedSync()
    suspend fun onError(throwable: Throwable)
    suspend fun onSendSync()
}

class SyncStatusTaskUseCaseImpl @Inject constructor():SyncStatusTaskUseCase{

    private val statusFlow = MutableStateFlow<SyncStatus>(SyncStatus.NothingSync)

    override fun invoke(): Flow<SyncStatus>  = statusFlow
    override suspend fun startSync() {
        statusFlow.emit(SyncStatus.StartSync)
    }

    override suspend fun onCompletedSync() {
        statusFlow.emit(SyncStatus.CompletedSync)
    }

    override suspend fun onError(throwable: Throwable) {
        Log.i(this.javaClass.name,"onError: $throwable")
        statusFlow.emit(SyncStatus.Error)
    }

    override suspend fun onSendSync() {
        statusFlow.emit(SyncStatus.SendSync)
    }
}

sealed class SyncStatus{
    data object StartSync:SyncStatus()
    data object CompletedSync:SyncStatus()
    data object Error:SyncStatus()
    data object NothingSync:SyncStatus()
    data object SendSync:SyncStatus()
}