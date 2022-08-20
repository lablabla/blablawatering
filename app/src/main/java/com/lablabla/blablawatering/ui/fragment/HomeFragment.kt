package com.lablabla.blablawatering.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lablabla.blablawatering.R
import com.lablabla.blablawatering.adapters.StationsAdapter
import com.lablabla.blablawatering.databinding.FragmentHomeBinding
import com.lablabla.blablawatering.model.Device
import com.lablabla.blablawatering.ui.MainViewModel
import com.lablabla.blablawatering.utils.navigateSafe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var stationsAdapter: StationsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setupRecyclerView()

        stationsAdapter.setOnItemClickListener { station ->
            val bundle = Bundle().apply {
                putSerializable("station", station)
            }
            findNavController().navigateSafe(R.id.action_homeFragment_to_stationFragment, bundle)
        }

        viewModel.viewModelScope.launch {
            viewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is MainViewModel.UIEvent.StationsSynced -> {
                        if (event.success) {
                            viewModel.getLocalStations()
                        }
                    }
                    is MainViewModel.UIEvent.DeviceUpdated -> {
                        updateDevices(event.deviceList)
                    }
                    is MainViewModel.UIEvent.StationsUpdated -> {
                        if (event.stationsList.isEmpty()) {
                            viewModel.syncStations()
                        }
                        else {
                            stationsAdapter.differ.submitList(event.stationsList)
                        }
                    }
                    is MainViewModel.UIEvent.ShowSnackbar -> {
                        val snackbar = Snackbar.make(
                            view,
                            event.message,
                            Snackbar.LENGTH_SHORT
                        )
                        snackbar.show()
                    }
                    is MainViewModel.UIEvent.SetProgressBarVisibility -> {
                        binding.mainProgressBar.visibility = event.visibility
                    }
                }
            }
        }
        viewModel.getDevices()
        viewModel.getLocalStations()
    }

    private fun updateDevices(deviceList: List<Device>) {
        if (deviceList.isNotEmpty()) {
            binding.deviceCVName.text = "Name: ${deviceList[0].name}"
            binding.deviceCVAddress.text = "Address: ${deviceList[0].address}"
        }
    }

    private fun setupRecyclerView() {
        stationsAdapter = StationsAdapter()
        binding.stationsRecyclerView.apply {
            adapter = stationsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}