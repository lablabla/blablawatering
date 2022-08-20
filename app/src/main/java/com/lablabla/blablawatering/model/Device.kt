package com.lablabla.blablawatering.model

import com.lablabla.blablawatering.data.local.entity.DeviceEntity
import com.lablabla.blablawatering.model.enums.DeviceStatus

class Device(
    val name: String,
    val address: String,
    val deviceStatus: DeviceStatus
) {
    fun toEntity(): DeviceEntity {
        return DeviceEntity(
            name = name,
            address = address,
            deviceStatus = deviceStatus.ordinal
        )
    }

}