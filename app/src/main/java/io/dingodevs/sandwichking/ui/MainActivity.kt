package io.dingodevs.sandwichking.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.dingodevs.sandwichking.R

class MainActivity : AppCompatActivity() {
    override fun onSupportNavigateUp() =
        findNavController(R.id.main_navigation_fragment).navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.main_navigation_fragment)

        navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController)
    }
}
