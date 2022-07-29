package com.lablabla.blablawatering.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil.convertBrIdToString
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lablabla.blablawatering.R
import com.lablabla.blablawatering.databinding.ActivityMainBinding
import com.lablabla.blablawatering.databinding.FragmentStatusBinding
import com.lablabla.blablawatering.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatusFragment : Fragment(R.layout.fragment_status) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentStatusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStatusBinding.bind(view)
    }
}