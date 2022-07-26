package com.lablabla.blablawatering.model

import com.lablabla.blablawatering.data.local.entity.StationEntity
import com.lablabla.blablawatering.model.devices.Solenoid
import com.lablabla.blablawatering.model.enums.SolenoidState
import com.lablabla.blablawatering.model.enums.WateringState

class Station(
    private val id: Int,
    private val name: String,
    private val solenoid: Solenoid,
    ) {

    public fun onEvent(event: Event) {
        val data = if (event.wateringState == WateringState.On) SolenoidState.Open else SolenoidState.Closed
        solenoid.setData(data)
    }

    fun toEntity(): StationEntity {
        return StationEntity(
            id = id,
            name = name,
            solenoid = solenoid.toEntity()
        )
    }
}