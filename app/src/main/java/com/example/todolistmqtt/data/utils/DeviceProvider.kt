package com.example.todolistmqtt.data.utils

import android.content.SharedPreferences
import com.example.todolistmqtt.data.local.shared.SharedConstants
import java.util.UUID
import javax.inject.Inject

class DeviceProvider @Inject constructor(private val sharedPreferences: SharedPreferences) {
    private var _id: String = getIdFromShared()

    val id: String = _id

    @Synchronized
    private fun getIdFromShared(): String {
        return sharedPreferences.getString(SharedConstants.SHARED_UUID, null)?.let {
            _id = it
            _id
        } ?: run {
            val uuid = UUID.randomUUID().toString()
            sharedPreferences.edit().putString(SharedConstants.SHARED_UUID, uuid).apply()
            _id = uuid
            _id
        }
    }

    companion object {
        private val TAG = DeviceProvider::class.simpleName
    }
}