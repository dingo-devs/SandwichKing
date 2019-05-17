package io.dingodevs.sandwichking.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.dingodevs.sandwichking.R

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        setNavigationViewFragment(item.itemId)
        true
    }

    private fun setNavigationViewFragment(menuItemId: Int) {
        val fragment = when (menuItemId) {
            R.id.navigation_sandwich -> CreateSandwichFragment.newInstance()
            R.id.navigation_settings -> ArduinoSettingsFragment.newInstance()
            else -> throw Throwable("Invalid navigation menu item selected")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        setNavigationViewFragment(navView.selectedItemId)
    }
}
