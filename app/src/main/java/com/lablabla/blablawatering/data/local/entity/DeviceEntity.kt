package com.lablabla.blablawatering.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lablabla.blablawatering.model.Device
import com.lablabla.blablawatering.model.enums.DeviceStatus

@Entity
class DeviceEntity(
    val name: String,
    val address: String,
    val deviceStatus: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun toDevice(): Device {
        return Device(
            name = name,
            address = address,
            deviceStatus = DeviceStatus.getByValue(deviceStatus)?: DeviceStatus.Offline
        )
    }
}