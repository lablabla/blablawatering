package com.lablabla.blablawatering.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lablabla.blablawatering.data.repository.DeviceRepository
import com.lablabla.blablawatering.data.repository.StationsRepository
import com.lablabla.blablawatering.model.Device
import com.lablabla.blablawatering.model.Station
import com.lablabla.blablawatering.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val stationsRepository: StationsRepository,
    val deviceRepository: DeviceRepository
            ): ViewModel() {

    protected val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    open class Event
    open fun onEvent(event: Event) {

    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
        data class SetProgressBarVisibility(val visibility: Int): UIEvent()
        data class DeviceUpdated(val deviceList: List<Device>): UIEvent()
        data class StationsUpdated(val stationsList: List<Station>): UIEvent()
        data class StationsSynced(val success: Boolean): UIEvent()
    }

    init {
        viewModelScope.launch {
            deviceRepository.init()
        }
    }

    fun syncStations() {
        viewModelScope.launch {
            // Update the repository to the latest data from the remote device
            stationsRepository.syncStations().collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let {
                            Log.d("GetStations", "Received Success with ${it.size} stations")
                        }
                        _eventFlow.emit(UIEvent.SetProgressBarVisibility(View.INVISIBLE))
                        _eventFlow.emit(UIEvent.ShowSnackbar("Sync complete"))
                        _eventFlow.emit(UIEvent.StationsSynced(true))
                    }
                    is Resource.Error -> {
                        Log.d("GetStations", "Received Error: ${result.message ?: "Unknown error"}")
                        _eventFlow.emit(UIEvent.SetProgressBarVisibility(View.INVISIBLE))
                        _eventFlow.emit(UIEvent.ShowSnackbar("Sync failed:  ${result.message ?: "Unknown error"}"))
                        _eventFlow.emit(UIEvent.StationsSynced(false))
                    }
                    is Resource.Loading -> {
                        Log.d("GetStations", "Received Loading")
                        _eventFlow.emit(UIEvent.SetProgressBarVisibility(View.VISIBLE))
                    }
                }
            }
        }
    }

    fun getLocalStations() {
        viewModelScope.launch {
            _eventFlow.emit(UIEvent.StationsUpdated(stationsRepository.getLocalStations()))
        }
    }

    fun getDevices() {
        viewModelScope.launch {
            _eventFlow.emit(UIEvent.DeviceUpdated(deviceRepository.getDevices()))
        }
    }
}