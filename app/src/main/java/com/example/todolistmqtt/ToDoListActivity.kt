package com.example.todolistmqtt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.todolistmqtt.ui.worker.TaskSyncManager
import com.example.todolistmqtt.ui.feature.navigation.NavigationRoute
import com.example.todolistmqtt.ui.theme.ToDoListMqttTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ToDoListActivity : ComponentActivity() {

    @Inject
    lateinit var taskSyncManager: TaskSyncManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToDoListMqttTheme {
                NavigationRoute()
            }
        }

        taskSyncManager.startPeriodicSync()
    }

    override fun onDestroy() {
        super.onDestroy()

        taskSyncManager.stopPeriodicSync()
    }
}