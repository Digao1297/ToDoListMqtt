package com.example.todolistmqtt.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.todolistmqtt.data.local.shared.SharedConstants
import com.example.todolistmqtt.data.remote.di.annotation.ClientID
import com.example.todolistmqtt.data.utils.DeviceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DeviceModule {

    @Provides
    @ClientID
    fun provideDeviceId(
        sharedPreferences: SharedPreferences,
    ): String {
        return DeviceProvider(sharedPreferences).id
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SharedConstants.SHARED_NAME, Context.MODE_PRIVATE)
    }
}