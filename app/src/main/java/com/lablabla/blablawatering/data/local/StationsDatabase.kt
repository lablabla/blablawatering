package com.lablabla.blablawatering.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lablabla.blablawatering.data.local.entity.StationEntity

@Database(
    entities = [StationEntity::class],
    version = 1
)

abstract class StationsDatabase: RoomDatabase() {
    abstract val dao: StationDao
}