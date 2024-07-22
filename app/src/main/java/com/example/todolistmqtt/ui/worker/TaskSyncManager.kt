package com.example.todolistmqtt.ui.worker

interface TaskSyncManager {
    fun startPeriodicSync()
    fun stopPeriodicSync()
}