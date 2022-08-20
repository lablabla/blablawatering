package com.lablabla.blablawatering.data.local

import androidx.room.*
import com.lablabla.blablawatering.data.local.entity.DeviceEntity
import com.lablabla.blablawatering.data.local.entity.StationEntity

@Dao
interface WateringDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStation(station: StationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(user: List<StationEntity>)

    @Delete
    suspend fun deleteStation(station: StationEntity)

    @Query("SELECT * FROM stationentity")
    suspend fun getStations(): List<StationEntity>

    @Query("DELETE from stationentity")
    suspend fun deleteAllStations()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevice(device: DeviceEntity)

    @Query("SELECT * FROM deviceentity")
    suspend fun getDevices(): List<DeviceEntity>
}