package com.example.todolistmqtt.ui.feature.task

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolistmqtt.R
import com.example.todolistmqtt.domain.model.Task
import com.example.todolistmqtt.ui.theme.ToDoListMqttTheme

@Composable
fun TaskRoute(
    viewModel: TaskViewModel = hiltViewModel<TaskViewModel>(),
    navController: NavController,
) {

    val context = LocalContext.current

    val listTasksState = viewModel.listTasksState.collectAsState()

    val createTaskErrorString = stringResource(id = R.string.create_task_error)
    val createTaskSuccessString = stringResource(id = R.string.create_task_success)

    LaunchedEffect(Unit) {
        viewModel.createTaskState.collect { result ->
            when (result) {
                TaskViewModel.CreateTaskState.Empty -> {}
                TaskViewModel.CreateTaskState.Error -> showToast(context, createTaskErrorString)
                TaskViewModel.CreateTaskState.Success -> showToast(context, createTaskSuccessString)
            }
        }
    }

    TaskScreen(context = context, uiState = listTasksState)
}


@Composable
private fun TaskScreen(context: Context, uiState: State<TaskViewModel.ListTasksState>) {
    Scaffold(floatingActionButton = {
        FloatingActionButtonWithDialog()
    }, content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
        ) {
            when (val state = uiState.value) {
                is TaskViewModel.ListTasksState.Empty -> EmptyListMessage(padding = padding)
                is TaskViewModel.ListTasksState.Error -> {
                    LaunchedEffect(key1 = state) {
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    }
                    EmptyListMessage(padding)
                }

                is TaskViewModel.ListTasksState.Success -> ListTasks(state.tasks)
                TaskViewModel.ListTasksState.Loading -> Loading(padding)
            }
        }
    })
}

@Composable
private fun EmptyListMessage(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Empty", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun Loading(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ListTasks(tasks: List<Task>) {
    LazyColumn {
        items(tasks.size) { pos ->
            TaskItemWidget(item = tasks[pos])
        }
    }
}


@Composable
private fun FloatingActionButtonWithDialog(viewModel: TaskViewModel = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }

    FloatingActionButton(onClick = { showDialog = true },
        modifier = Modifier.padding(16.dp),
        content = {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        })

    if (showDialog) {
        var value by remember { mutableStateOf("") }

        AlertDialog(onDismissRequest = {
            showDialog = false
        }, title = {
            Text(text = "Enter Data")
        }, text = {


            Column {
                TextField(modifier = Modifier.padding(vertical = 4.dp),
                    value = value,
                    onValueChange = { value = it },
                    label = { Text("Task here") })
            }
        }, confirmButton = {
            Button(onClick = {
                showDialog = false
                val task = Task(
                    value = value,
                )
                viewModel.createTask(task)
            }) {
                Text("Send")
            }
        }, dismissButton = {
            Button(onClick = { showDialog = false }) {
                Text("Cancel")
            }
        })
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@Preview(showBackground = true, name = "Success State")
@Composable
private fun TaskSuccessPreview() {
    val context = LocalContext.current
    val tasks = listOf(Task(id = 1, value = "test"))
    val uiState = remember { mutableStateOf(TaskViewModel.ListTasksState.Success(tasks)) }

    ToDoListMqttTheme {
        TaskScreen(
            context,
            uiState
        )
    }
}

@Preview(showBackground = true, name = "Empty State")
@Composable
private fun TaskEmptyPreview() {
    val context = LocalContext.current
    val uiState = remember { mutableStateOf(TaskViewModel.ListTasksState.Empty) }

    ToDoListMqttTheme {
        TaskScreen(
            context,
            uiState
        )
    }
}