package com.example.todolistmqtt.data.remote.mqtt

import kotlinx.coroutines.flow.Flow

interface MQTTClient {
    fun connect()
    fun disconnect()
    fun publish(topic: String, payload: ByteArray, qos: Int = 1, retained: Boolean = false)
    fun subscribe(topic: String, qos: Int = 1)
    fun unsubscribe(topic: String, qos: Int = 1)
}