package com.lablabla.blablawatering.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.lablabla.blablawatering.R
import com.lablabla.blablawatering.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.wateringNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        val mainViewModel: MainViewModel by viewModels()
        lifecycleScope.launch {
            mainViewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is MainViewModel.UIEvent.ShowSnackbar -> {
                        val snackbar = Snackbar.make(
                            view,
                            event.message,
                            Snackbar.LENGTH_SHORT
                        )
                        snackbar.show()
                    }
                    is MainViewModel.UIEvent.SetProgressBarVisibility -> {
                        binding.progressBar.visibility = event.visibility
                    }
                }
            }
        }
    }
}
