package com.lablabla.blablawatering.model.enums

enum class DeviceStatus {
    Connected,
    Disconnected,
    Offline;

    companion object {
        private val VALUES = DeviceStatus.values()
        fun getByValue(value: Int) = VALUES.firstOrNull { it.ordinal == value }
    }
}