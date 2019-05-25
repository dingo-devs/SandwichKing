package io.dingodevs.sandwichking.ui

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.dingodevs.sandwichking.R
import io.dingodevs.sandwichking.viewmodel.ArduinoSettingsViewModel


class ArduinoSettingsFragment : Fragment() {
    private lateinit var viewModel: ArduinoSettingsViewModel
    private val bluetoothAdapter: BluetoothAdapter by lazy { BluetoothAdapter.getDefaultAdapter() }
    private val REQUEST_ENABLE_BT = 1
    private val ARDUINO_BT_ADDRESS = "F4:C2:48:44:3F:D5"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ArduinoSettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.arduino_settings_fragment, container, false)

        root.findViewById<Button>(R.id.btnBluetoothScan).setOnClickListener {
            if (bluetoothAdapter.isEnabled) {
                connectToArduino()
            } else {
                val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BT)
            }
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_ENABLE_BT -> {
                if (resultCode != Activity.RESULT_OK) {
                    Toast.makeText(context, "Please enable bluetooth", Toast.LENGTH_LONG).show()
                    return
                }

                connectToArduino()
            }
        }
    }

    fun connectToArduino() {
        val arduinoBluetoothDevice = findArduinoDevice()
        if (null == arduinoBluetoothDevice) {
            Toast.makeText(context, "Could not find arduino (${ARDUINO_BT_ADDRESS})", Toast.LENGTH_LONG).show()
        }
    }

    fun findArduinoDevice(): BluetoothDevice? {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter.bondedDevices
        pairedDevices?.forEach { device -> println("${device.name}: Address: ${device.address}") }
        return pairedDevices?.filter { device ->
            device.bondState == BluetoothDevice.BOND_BONDED &&
                    device.address == ARDUINO_BT_ADDRESS
        }?.firstOrNull()
    }
}
