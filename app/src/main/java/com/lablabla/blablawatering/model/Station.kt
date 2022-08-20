package com.lablabla.blablawatering.model

import com.lablabla.blablawatering.data.local.entity.StationEntity
import com.lablabla.blablawatering.model.devices.Solenoid
import com.lablabla.blablawatering.model.enums.SolenoidState
import com.lablabla.blablawatering.model.enums.WateringState
import java.io.Serializable

class Station(
    val id: Int,
    val name: String,
    val solenoid: Solenoid,
    ) : Serializable{

    fun onEvent(event: Event) {
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