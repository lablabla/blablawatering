package com.lablabla.blablawatering.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lablabla.blablawatering.data.repository.StationsRepository

@Suppress("UNCHECKED_CAST")
class StatusViewModelProviderFactory(
    private val stationsRepository: StationsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(stationsRepository) as T
    }
}