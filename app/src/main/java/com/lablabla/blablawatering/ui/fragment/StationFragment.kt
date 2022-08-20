package com.lablabla.blablawatering.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.lablabla.blablawatering.R
import com.lablabla.blablawatering.databinding.FragmentHomeBinding
import com.lablabla.blablawatering.databinding.FragmentStationBinding
import com.lablabla.blablawatering.ui.MainViewModel

class StationFragment : Fragment(R.layout.fragment_station) {

    val args: StationFragmentArgs by navArgs()
    private lateinit var binding: FragmentStationBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStationBinding.bind(view)

        val station = args.station
        binding.sfTextView.text = station.name
    }
}