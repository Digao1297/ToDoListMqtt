package com.example.todolistmqtt.ui.feature.task

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistmqtt.common.ResultStatus
import com.example.todolistmqtt.domain.model.Task
import com.example.todolistmqtt.domain.usecase.task.CreateTaskUseCase
import com.example.todolistmqtt.domain.usecase.task.ReadTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase,
    private val readTasksUseCase: ReadTasksUseCase,
) : ViewModel() {

    private val _listTasksState = MutableStateFlow<ListTasksState>(ListTasksState.Empty)
    val listTasksState: StateFlow<ListTasksState> = _listTasksState.asStateFlow()

    private val _createTaskState = MutableStateFlow<CreateTaskState>(CreateTaskState.Empty)
    val createTaskState: StateFlow<CreateTaskState> = _createTaskState.asStateFlow()

    init {
        refreshListTasks()
    }

    fun createTask(task: Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val useCaseFlow = createTaskUseCase(task)
                useCaseFlow.collect { result ->
                    when (result) {
                        ResultStatus.Loading -> _listTasksState.value = ListTasksState.Loading
                        is ResultStatus.Error -> _createTaskState.value = CreateTaskState.Error
                        is ResultStatus.Success -> {
                            refreshListTasks()
                            CreateTaskState.Success
                        }
                    }
                }
            }
        }
    }

    private fun refreshListTasks() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val useCaseFlow = readTasksUseCase()
                useCaseFlow.collect { result ->
                    _listTasksState.value = when (result) {
                        ResultStatus.Loading -> ListTasksState.Loading
                        is ResultStatus.Error -> ListTasksState.Error
                        is ResultStatus.Success -> {
                            Log.d("State",result.toString())
                            if (result.data.isEmpty()) {
                                ListTasksState.Empty
                            } else {
                                ListTasksState.Success(result.data)
                            }
                        }
                    }
                }
            }
        }
    }

    sealed class ListTasksState {
        data class Success(val tasks: List<Task>) : ListTasksState()
        object Error : ListTasksState()
        object Empty : ListTasksState()
        object Loading : ListTasksState()
    }

    sealed class CreateTaskState {
        object Success : CreateTaskState()
        object Error : CreateTaskState()
        object Empty : CreateTaskState()
    }
}