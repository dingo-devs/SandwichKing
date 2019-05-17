package io.dingodevs.sandwichking.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.dingodevs.sandwichking.R
import io.dingodevs.sandwichking.viewmodel.ArduinoSettingsViewModel


class ArduinoSettingsFragment : Fragment() {
    private lateinit var viewModel: ArduinoSettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.arduino_settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArduinoSettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = ArduinoSettingsFragment()
    }
}
