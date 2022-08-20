package com.lablabla.blablawatering.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lablabla.blablawatering.data.local.entity.DeviceEntity
import com.lablabla.blablawatering.data.local.entity.StationEntity

@Database(
    entities = [StationEntity::class, DeviceEntity::class],
    version = 1
)

abstract class WateringDatabase: RoomDatabase() {
    abstract val dao: WateringDao
}