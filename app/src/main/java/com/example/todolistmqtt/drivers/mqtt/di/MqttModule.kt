package com.example.todolistmqtt.drivers.mqtt.di

import android.content.Context
import com.example.todolistmqtt.BuildConfig
import com.example.todolistmqtt.drivers.mqtt.MQTTStateFlow
import com.example.todolistmqtt.drivers.mqtt.di.annotation.ClientID
import com.example.todolistmqtt.drivers.mqtt.di.annotation.ServerURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.eclipse.paho.android.service.MqttAndroidClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MqttModule {

    @Provides
    @Singleton
    fun providesMqttClient(
        @ApplicationContext context: Context,
        @ServerURL serverUrl: String,
        @ClientID clientId: String
    ): MqttAndroidClient =
        MqttAndroidClient(context, serverUrl, clientId)

    @Provides
    @Singleton
    @ServerURL
    fun provideServerUrl(): String {
        return BuildConfig.MQTT_SERVER
    }

    @Provides
    fun provideMQTTStateFlow(): MQTTStateFlow {
        return MQTTStateFlow()
    }
}