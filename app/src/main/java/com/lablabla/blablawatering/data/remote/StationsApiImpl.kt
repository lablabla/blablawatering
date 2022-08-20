package com.lablabla.blablawatering.data.remote

import com.lablabla.blablawatering.communication.MqttApi
import com.lablabla.blablawatering.model.Station
import com.lablabla.blablawatering.model.devices.Solenoid
import com.lablabla.blablawatering.model.enums.SolenoidState

class StationsApiImpl(
    private val mqtt: MqttApi
) : StationsApi {
    override fun getStations(): List<Station> {
        return listOf(
            Station(0, "Test0", Solenoid(0, SolenoidState.Open)),
            Station(1, "Test1", Solenoid(1, SolenoidState.Closed)),
            Station(2, "Test2", Solenoid(2, SolenoidState.Open)),
        )
    }
}