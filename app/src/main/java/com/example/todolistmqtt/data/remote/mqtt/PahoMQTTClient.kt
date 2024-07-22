package com.example.todolistmqtt.data.remote.mqtt

import android.util.Log
import com.example.todolistmqtt.data.remote.di.annotation.ClientID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import javax.inject.Inject

class PahoMQTTClient @Inject constructor(
    private val mqttClient: MqttAndroidClient,
    private val mqttStateFlow: MQTTStateFlow,

) : MQTTClient {

    companion object {
        private const val TAG_MQTT = "MQTT-PAHO-SERVICE"
    }

    override fun connect() {
        mqttClient.setCallback(mqttCallback)

        val options = MqttConnectOptions().apply {
            userName = "test"
            isAutomaticReconnect = true
        }

        mqttClient.connect(options, null, mqttActionListener)
    }

    override fun disconnect() {
        mqttClient.disconnect(null, mqttActionListener)
    }

    override fun publish(topic: String, payload: ByteArray, qos: Int, retained: Boolean) {
        val msg = MqttMessage().apply {
            this.payload = payload
            this.qos = qos
            isRetained = retained
        }
        mqttClient.publish(topic, msg, null, mqttActionListener)
    }

    override fun subscribe(topic: String, qos: Int) {
        mqttClient.subscribe(topic, qos, null, mqttActionListener)
    }

    override fun unsubscribe(topic: String, qos: Int) {
        mqttClient.unsubscribe(topic)
    }


    private val mqttActionListener = object : IMqttActionListener {
        override fun onSuccess(asyncActionToken: IMqttToken?) {
            Log.d(TAG_MQTT, "Connection Success")
            CoroutineScope(Dispatchers.IO).launch {
                mqttStateFlow.emitState(MQTTState.Success)
            }
        }

        override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
            Log.d(TAG_MQTT, "Connection failure: ${exception?.stackTraceToString()}")
            CoroutineScope(Dispatchers.IO).launch {
                if (exception != null) {
                    mqttStateFlow.emitState(MQTTState.Error(exception))
                } else {
                    mqttStateFlow.emitState(MQTTState.Error(Exception()))
                }
            }
        }

    }

    private val mqttCallback = object : MqttCallbackExtended {

        override fun connectComplete(reconnect: Boolean, serverURI: String?) {
            if (reconnect) {
                Log.d(TAG_MQTT, "Connection complete $serverURI")
                subscribe(MQTTConstants.TOPIC_SYNCHRONIZED)
            }
        }

        override fun connectionLost(cause: Throwable?) {
            Log.d(TAG_MQTT, "Connection lost ${cause.toString()}")
        }

        override fun messageArrived(topic: String?, message: MqttMessage?) {
            val msg = "Receive message: ${message.toString()} from topic: $topic"
            Log.d(TAG_MQTT, msg)

            val topicParts = topic?.split("/")
            if(topicParts !=null && topicParts.size >= 2){
                val clientId = topicParts[1]

                if(mqttClient.clientId != clientId){

                }
            }else {
              Log.d(TAG_MQTT, "ClientID Not Found!!")
            }


        }

        override fun deliveryComplete(token: IMqttDeliveryToken?) {
            Log.d(TAG_MQTT, "Delivery complete")
        }

    }
}