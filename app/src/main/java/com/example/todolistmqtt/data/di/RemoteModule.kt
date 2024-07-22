package com.example.todolistmqtt.data.di

import com.example.todolistmqtt.data.remote.mqtt.MQTTClient
import com.example.todolistmqtt.data.remote.mqtt.PahoMQTTClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {

    @Binds
    @Singleton
    fun bindMqttClient(mqttClient: PahoMQTTClient): MQTTClient
}