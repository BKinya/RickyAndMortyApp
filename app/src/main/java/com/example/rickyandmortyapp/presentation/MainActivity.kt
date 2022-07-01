package com.example.rickyandmortyapp.presentation

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.rickyandmortyapp.R
import com.example.rickyandmortyapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  private val rickyViewModel: RickyViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    installSplashScreen()
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    getUserName()
    checkIfRegistered()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  private fun checkIfRegistered() {
    rickyViewModel.name.observe(this) { name ->
      if (name != null) {
        setStartDestination(R.id.SecondFragment)
      } else {
        setStartDestination(R.id.FirstFragment)
      }
    }
  }

  private fun getUserName() {
    rickyViewModel.getUserName(this)
  }

  private fun setStartDestination(startDestinationId: Int){
    val navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    val navController = navHost.findNavController()
    val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
    navGraph.setStartDestination(startDestinationId)
    navController.graph = navGraph
  }
}