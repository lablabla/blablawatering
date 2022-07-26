package com.lablabla.blablawatering.model.devices

import com.lablabla.blablawatering.data.local.entity.SolenoidEntity
import com.lablabla.blablawatering.model.enums.SolenoidState

class Solenoid(
    val pinNumber: Int,
    private var state: SolenoidState
    ) {

    fun setData(newState: SolenoidState) {
        state = newState

        // Write to output pin

    }

    fun toEntity(): SolenoidEntity {
        return SolenoidEntity(
            pinNumber = pinNumber,
            state = state.ordinal
        )
    }
}