package io.dingodevs.sandwichking.repository

import android.content.Context
import android.content.SharedPreferences
import io.dingodevs.sandwichking.entity.SandwichIngredient
import io.dingodevs.sandwichking.entity.SandwichIngredientCalibrationSettings
import io.dingodevs.sandwichking.entity.SandwichIngredientMovement

class CalibrationSettingsRepository {
    private val preferencesName = "calibration_settings"
    private val calibrationKeyPrefix = "calibration_"

    fun getCalibrationSettingsForIngredient(
        context: Context,
        sandwichIngredient: SandwichIngredient
    ): SandwichIngredientCalibrationSettings {
        val prefs = getSharedPreferences(context)
        val movementStringSet =
            prefs.getString(calibrationKeyPrefix + sandwichIngredient.id, null)


        return if (movementStringSet == null)
            SandwichIngredientCalibrationSettings(sandwichIngredient) else
            deserializeMovements(movementStringSet)
    }

    fun setCalibrationSettingsForIngredient(
        context: Context,
        sandwichIngredientCalibrationSettings: SandwichIngredientCalibrationSettings
    ) {
        val prefEditor = getSharedPreferencesEditor(context)
        prefEditor.putString(
            calibrationKeyPrefix + sandwichIngredientCalibrationSettings.sandwichIngredient.id,
            serializeMovements(sandwichIngredientCalibrationSettings)
        )
        prefEditor.commit()
    }

    private fun serializeMovements(sandwichIngredientCalibrationSettings: SandwichIngredientCalibrationSettings): String {
        val movementList = sandwichIngredientCalibrationSettings.movementSequence
        val sep = "|"
        val movementListString = ""
        movementList.joinToString {
            it.order.toString().plus(sep).plus(it.servo.id).plus(sep).plus(it.targetPosition.toString())
        }

        return sandwichIngredientCalibrationSettings.sandwichIngredient.id.toString()
            .plus("@")
            .plus(movementListString)
    }


    private fun deserializeMovements(serializedCalibrationSettings: String): SandwichIngredientCalibrationSettings {
        val splitMembers = serializedCalibrationSettings.split("@")
        val sandwichIngredientId = splitMembers[0].toInt()

        val movementListString = splitMembers[1].split(",")
        val movementList = mutableListOf<SandwichIngredientMovement>()
        movementListString.forEach {
            val movementListMembers = it.split("|")
            val order = movementListMembers[0].toInt()
            val servoId = movementListMembers[1].toInt()
            val targetPosition = movementListMembers[2].toInt()

            movementList.add(SandwichIngredientMovement(ArduinoServoRepository.getById(servoId), targetPosition, order))
        }


        val settings = SandwichIngredientCalibrationSettings(SandwichIngredientRepository.getById(sandwichIngredientId))
        settings.setMovementSequence(movementList)

        return settings
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
    }


    private fun getSharedPreferencesEditor(context: Context): SharedPreferences.Editor {
        return getSharedPreferences(context).edit()
    }
}