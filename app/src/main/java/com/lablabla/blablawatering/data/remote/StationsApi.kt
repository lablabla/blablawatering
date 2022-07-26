package com.lablabla.blablawatering.data.remote

import com.lablabla.blablawatering.model.Station

interface StationsApi {
    fun getStations() : List<Station>
}