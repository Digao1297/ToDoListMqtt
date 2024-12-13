package com.example.todolistmqtt.ui.feature.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistmqtt.domain.usecase.ui.SyncStatus
import com.example.todolistmqtt.domain.usecase.ui.SyncStatusTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyncStatusTaskViewModel @Inject constructor(
    private val syncStatusUseCase: SyncStatusTaskUseCase,
) : ViewModel() {

    private val _statusFlow = MutableStateFlow<SyncStatus>(SyncStatus.NothingSync)
    val statusFlow: StateFlow<SyncStatus> = _statusFlow

    init {
        observeSyncStatus()
    }

    private fun observeSyncStatus() {
        viewModelScope.launch {
            syncStatusUseCase().collect { status ->
                _statusFlow.value = status
            }
        }
    }

}