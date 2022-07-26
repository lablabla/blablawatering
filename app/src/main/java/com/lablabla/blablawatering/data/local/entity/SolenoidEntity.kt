package com.lablabla.blablawatering.data.local.entity

import androidx.room.Entity
import com.lablabla.blablawatering.model.devices.Solenoid
import com.lablabla.blablawatering.model.enums.SolenoidState

@Entity
data class SolenoidEntity(
    val pinNumber: Int,
    val state: Int,
) {
    fun toSolenoid(): Solenoid {
        return Solenoid(
            pinNumber = pinNumber,
            state = SolenoidState.getByValue(state) ?: SolenoidState.Closed
        )
    }
}