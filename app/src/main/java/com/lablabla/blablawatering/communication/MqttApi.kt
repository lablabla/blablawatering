package com.lablabla.blablawatering.communication

interface MqttApi {
    fun connect(address: String, username: String, password: String)

    fun disconnect()

    fun subscribe(topic: String, QoS: Int = 1)

    fun unsubscribe(topic: String)

    fun publish(topic: String, message: String, QoS: Int = 1, retained: Boolean = false)
}