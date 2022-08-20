package com.lablabla.blablawatering.data.repository

import android.util.Log
import com.lablabla.blablawatering.data.local.WateringDao
import com.lablabla.blablawatering.data.local.entity.DeviceEntity
import com.lablabla.blablawatering.model.Device
import com.lablabla.blablawatering.model.enums.DeviceStatus
import kotlinx.coroutines.runBlocking

class DeviceRepositoryImpl(
    private val dao: WateringDao
): DeviceRepository {

    override suspend fun init() {
        try {
            runBlocking {
                val devices = getDevices()
                if (devices.isEmpty()) {
                    dao.insertDevice(
                        DeviceEntity(
                            "Device 0",
                            "0xABCD",
                            DeviceStatus.Disconnected.ordinal
                        )
                    )
                }
            }
        }
        catch (exception: Exception)
        {
            Log.e("DeviceRepositoryImpl", exception.message!!)
        }
    }

    override suspend fun getDevices(): List<Device> {
        return dao.getDevices().map { it.toDevice() }
    }
}