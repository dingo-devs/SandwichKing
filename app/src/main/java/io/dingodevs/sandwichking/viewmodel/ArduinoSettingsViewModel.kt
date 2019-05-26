package io.dingodevs.sandwichking.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import io.dingodevs.sandwichking.entity.SandwichIngredient
import io.dingodevs.sandwichking.entity.SandwichIngredientCalibrationSettings
import io.dingodevs.sandwichking.entity.SandwichIngredientMovement
import io.dingodevs.sandwichking.repository.ArduinoServo
import io.dingodevs.sandwichking.repository.CalibrationSettingsRepository
import io.dingodevs.sandwichking.repository.SandwichIngredientRepository

class ArduinoSettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val calibrationSettingsRepository = CalibrationSettingsRepository()
    val sandwichIngredientsList by lazy {
        SandwichIngredientRepository.sandwichIngredientData
    }

    fun getCalibarationSettings(sandwichIngredient: SandwichIngredient): SandwichIngredientCalibrationSettings {
        return calibrationSettingsRepository.getCalibrationSettingsForIngredient(getApplication(), sandwichIngredient)
    }

    fun setCalibarationSettings(
        sandwichIngredientCalibrationSettings: SandwichIngredientCalibrationSettings
    ) {
        calibrationSettingsRepository.setCalibrationSettingsForIngredient(
            getApplication(),
            sandwichIngredientCalibrationSettings
        )
    }

    fun sendNewMoveToArduino(sandwichIngredientMovement: SandwichIngredientMovement) {
        Log.i(
            "ArduinoSettings",
            "New move: ${sandwichIngredientMovement.servo.name}: ${sandwichIngredientMovement.targetPosition}"
        )
    }

    fun setAllArduinoServos(grip: Int, wristPitch: Int, wristRoll: Int, elbow: Int, shoulder: Int, waist: Int) {
        sendNewMoveToArduino(SandwichIngredientMovement(ArduinoServo.Elbow, elbow))
        sendNewMoveToArduino(SandwichIngredientMovement(ArduinoServo.Grip, grip))
        sendNewMoveToArduino(SandwichIngredientMovement(ArduinoServo.Shoulder, shoulder))
        sendNewMoveToArduino(SandwichIngredientMovement(ArduinoServo.Waist, waist))
        sendNewMoveToArduino(SandwichIngredientMovement(ArduinoServo.WristPitch, wristPitch))
        sendNewMoveToArduino(SandwichIngredientMovement(ArduinoServo.WristRoll, wristRoll))
    }
}
