package com.lablabla.blablawatering.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lablabla.blablawatering.data.repository.StationsRepository
import com.lablabla.blablawatering.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    stationsRepository: StationsRepository
            ): ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            // Update the repository to the latest data from the remote device
            stationsRepository.syncStations().collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let {
                            Log.d("GetStations", "Received Success with ${it.size} stations")
                        }
                    }
                    is Resource.Error -> {
                        Log.d("GetStations", "Received Error: ${result.message ?: "Unknown error"}")
                    }
                    is Resource.Loading -> {
                        Log.d("GetStations", "Received Loading")
                    }
                }
            }
            _isLoading.value = false
        }
    }
}