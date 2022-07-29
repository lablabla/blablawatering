package com.lablabla.blablawatering.data.repository

import com.lablabla.blablawatering.data.local.StationDao
import com.lablabla.blablawatering.data.remote.StationsApi
import com.lablabla.blablawatering.model.Station
import com.lablabla.blablawatering.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit

class StationsRepositoryImpl(
    private val api: StationsApi,
    private val dao: StationDao
) : StationsRepository {
    override fun syncStations(): Flow<Resource<List<Station>>> = flow {
        val stations = dao.getStations().map { it.toStation() }
        emit(Resource.Loading(data = stations))
        // Try get from remote to update new users
        try {
            TimeUnit.SECONDS.sleep(5)
            val remoteUsers = api.getStations()
            dao.deleteAllStations()
            dao.insertStations(remoteUsers.map { it.toEntity() })
        } catch (e: Exception) {
            emit(Resource.Error(data = stations, message = e.localizedMessage ?: "Unknown error occurred"))
        }


        val newStations = dao.getStations().map { it.toStation() }
        emit(Resource.Success(newStations))
    }

    override suspend fun getLocalStations(): List<Station> {
        return dao.getStations().map { it.toStation() }
    }
}