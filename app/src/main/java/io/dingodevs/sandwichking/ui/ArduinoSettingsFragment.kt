package io.dingodevs.sandwichking.ui

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.dingodevs.sandwichking.R
import io.dingodevs.sandwichking.entity.SandwichIngredient
import io.dingodevs.sandwichking.entity.SandwichIngredientCalibrationSettings
import io.dingodevs.sandwichking.entity.SandwichIngredientMovement
import io.dingodevs.sandwichking.repository.ArduinoServo
import io.dingodevs.sandwichking.viewmodel.ArduinoSettingsViewModel


class ArduinoSettingsFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var viewModel: ArduinoSettingsViewModel
    private lateinit var seekBarGrip: SeekBar
    private lateinit var seekBarWristPitch: SeekBar
    private lateinit var seekBarWristRoll: SeekBar
    private lateinit var seekBarElbow: SeekBar
    private lateinit var seekBarShoulder: SeekBar
    private lateinit var seekBarWaist: SeekBar
    private val bluetoothAdapter: BluetoothAdapter by lazy { BluetoothAdapter.getDefaultAdapter() }
    private val REQUEST_ENABLE_BT = 1
    private val ARDUINO_BT_ADDRESS = "F4:C2:48:44:3F:D5"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ArduinoSettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.arduino_settings_fragment, container, false)

        val ingredientSelection = root.findViewById<Spinner>(R.id.ingredientSelection)
        val adapter = ArrayAdapter<SandwichIngredient>(
            context!!,
            android.R.layout.simple_spinner_dropdown_item,
            viewModel.sandwichIngredientsList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ingredientSelection.adapter = adapter
        ingredientSelection.onItemSelectedListener = this@ArduinoSettingsFragment

        root.findViewById<Button>(R.id.btnBluetoothScan).setOnClickListener {
            if (bluetoothAdapter.isEnabled) {
                connectToArduino()
            } else {
                val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BT)
            }
        }

        seekBarGrip = root.findViewById(R.id.seekBarGrip)
        seekBarWristPitch = root.findViewById(R.id.seekBarWristPitch)
        seekBarWristRoll = root.findViewById(R.id.seekBarWristRoll)
        seekBarElbow = root.findViewById(R.id.seekBarElbow)
        seekBarShoulder = root.findViewById(R.id.seekBarShoulder)
        seekBarWaist = root.findViewById(R.id.seekBarWaist)

        seekBarGrip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.sendNewMoveToArduino(
                    SandwichIngredientMovement(
                        ArduinoServo.Grip,
                        seekBar?.progress!! + ArduinoServo.Grip.startRange
                    )
                )
            }
        })

        seekBarWristPitch.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.sendNewMoveToArduino(
                    SandwichIngredientMovement(
                        ArduinoServo.WristPitch,
                        seekBar?.progress!! + ArduinoServo.WristPitch.startRange
                    )
                )
            }
        })

        seekBarWristRoll.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.sendNewMoveToArduino(
                    SandwichIngredientMovement(
                        ArduinoServo.WristRoll,
                        seekBar?.progress!! + ArduinoServo.WristRoll.startRange
                    )
                )
            }
        })

        seekBarElbow.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.sendNewMoveToArduino(
                    SandwichIngredientMovement(
                        ArduinoServo.Elbow,
                        seekBar?.progress!! + ArduinoServo.Elbow.startRange
                    )
                )
            }
        })

        seekBarShoulder.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.sendNewMoveToArduino(
                    SandwichIngredientMovement(
                        ArduinoServo.Shoulder,
                        seekBar?.progress!! + ArduinoServo.Shoulder.startRange
                    )
                )
            }
        })

        seekBarWaist.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.sendNewMoveToArduino(
                    SandwichIngredientMovement(
                        ArduinoServo.Waist,
                        seekBar?.progress!! + ArduinoServo.Waist.startRange
                    )
                )
            }
        })

        seekBarGrip.max = ArduinoServo.Grip.endRange - ArduinoServo.Grip.startRange
        seekBarWristPitch.max = ArduinoServo.WristPitch.endRange - ArduinoServo.WristPitch.startRange
        seekBarWristRoll.max = ArduinoServo.WristRoll.endRange - ArduinoServo.WristRoll.startRange
        seekBarElbow.max = ArduinoServo.Elbow.endRange - ArduinoServo.Elbow.startRange
        seekBarShoulder.max = ArduinoServo.Shoulder.endRange - ArduinoServo.Shoulder.startRange
        seekBarWaist.max = ArduinoServo.Waist.endRange - ArduinoServo.Waist.startRange

        return root
    }

    // Item selected
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedSandwichIngredient = viewModel.sandwichIngredientsList[position]

        refreshCalibrationSettings(viewModel.getCalibarationSettings(selectedSandwichIngredient))
    }

    fun refreshCalibrationSettings(settings: SandwichIngredientCalibrationSettings) {
        var gripProgress = ArduinoServo.Grip.defaultPosition
        var wristPitchProgress = ArduinoServo.WristPitch.defaultPosition
        var wristRollProgress = ArduinoServo.WristRoll.defaultPosition
        var elbowProgress = ArduinoServo.Elbow.defaultPosition
        var shoulderProgress = ArduinoServo.Shoulder.defaultPosition
        var waistProgress = ArduinoServo.Waist.defaultPosition

        settings.movementSequence.forEach {
            when (it.servo) {
                ArduinoServo.Grip -> gripProgress = it.targetPosition
                ArduinoServo.WristPitch -> wristPitchProgress = it.targetPosition
                ArduinoServo.WristRoll -> wristRollProgress = it.targetPosition
                ArduinoServo.Elbow -> elbowProgress = it.targetPosition
                ArduinoServo.Shoulder -> shoulderProgress = it.targetPosition
                ArduinoServo.Waist -> waistProgress = it.targetPosition
            }
        }

        seekBarGrip.progress = gripProgress - ArduinoServo.Grip.startRange
        seekBarWristPitch.progress = wristPitchProgress - ArduinoServo.WristPitch.startRange
        seekBarWristRoll.progress = wristRollProgress - ArduinoServo.WristRoll.startRange
        seekBarElbow.progress = elbowProgress - ArduinoServo.Elbow.startRange
        seekBarShoulder.progress = shoulderProgress - ArduinoServo.Shoulder.startRange
        seekBarWaist.progress = waistProgress - ArduinoServo.Waist.startRange

        viewModel.setAllArduinoServos(
            gripProgress,
            wristPitchProgress,
            wristRollProgress,
            elbowProgress,
            shoulderProgress,
            waistProgress
        )
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}


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
