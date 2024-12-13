package com.example.todolistmqtt.drivers.worker

interface TaskSyncManager {
    fun startSyncManager()
    fun stopSyncManager()
}