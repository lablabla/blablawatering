package com.lablabla.blablawatering.data.repository

import com.lablabla.blablawatering.model.Device

interface DeviceRepository {
    suspend fun init()
    suspend fun getDevices(): List<Device>
}