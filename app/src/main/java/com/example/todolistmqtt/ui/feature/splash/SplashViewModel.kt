package com.example.todolistmqtt.ui.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.domain.usecase.communication.ConnectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val connectUseCase: ConnectUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()


    init {
        connect()
    }

    private fun connect() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                connectUseCase().collect { result ->
                    _uiState.value = when (result) {
                        ResultStatus.Loading -> UIState.Loading
                        is ResultStatus.Error -> UIState.Error
                        is ResultStatus.Success -> UIState.Success
                    }
                }
            }
        }
    }

    sealed class UIState {
        data object Success : UIState()
        data object Error : UIState()
        data object Loading : UIState()

    }
}