package com.lablabla.blablawatering.communication

class PahoMQTT: MqttApi {
    override fun connect(address: String, username: String, password: String) {
    }

    override fun disconnect() {
    }

    override fun subscribe(topic: String, QoS: Int) {
    }

    override fun unsubscribe(topic: String) {
    }

    override fun publish(topic: String, message: String, QoS: Int, retained: Boolean) {
    }
}