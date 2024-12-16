package com.example.todolistmqtt.data.di

import com.example.todolistmqtt.drivers.mqtt.MQTTClient
import com.example.todolistmqtt.drivers.mqtt.PahoMQTTClient
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