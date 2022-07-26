package com.lablabla.blablawatering.data.repository

import com.lablabla.blablawatering.model.Station
import com.lablabla.blablawatering.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StationsRepository {
    fun syncStations() : Flow<Resource<List<Station>>>
    suspend fun getLocalStations() : List<Station>
}