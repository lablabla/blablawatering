package com.lablabla.blablawatering.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lablabla.blablawatering.model.Station

@Entity
data class StationEntity(
    @PrimaryKey val id: Int,
    val name: String,
    @Embedded val solenoid: SolenoidEntity,
) {
    fun toStation(): Station {
        return Station(
            id = id,
            name = name,
            solenoid = solenoid.toSolenoid()
        )
    }
}