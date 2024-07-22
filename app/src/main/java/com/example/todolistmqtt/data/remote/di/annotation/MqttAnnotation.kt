package com.example.todolistmqtt.data.remote.di.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ServerURL

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ClientID