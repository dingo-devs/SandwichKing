package io.dingodevs.sandwichking.repository

import android.content.Context
import android.content.SharedPreferences
import io.dingodevs.sandwichking.entity.SandwichIngredient
import io.dingodevs.sandwichking.entity.SandwichIngredientCalibrationSettings
import io.dingodevs.sandwichking.entity.SandwichIngredientMovement

class CalibrationSettingsRepository(val context: Context) {
    private val PREFERENCES_NAME = "calibration_settings"
    private val CALIBRATION_KEY_PREFIX = "calibration_"
    fun getCalibrationSettingsForIngredient(val sandwichIngredient: SandwichIngredient): SandwichIngredientCalibrationSettings {
        val prefs = getSharedPreferences()
        val movementStringSet =
            prefs.getStringSet(CALIBRATION_KEY_PREFIX + sandwichIngredientCalibrationSettings.sandwichIngredient.id)

        return null
    }

    fun saveCalibrationSettingsForIngredient(val sandwichIngredientCalibrationSettings: SandwichIngredientCalibrationSettings) {
        val prefEditor = getSharedPreferencesEditor()
        prefEditor.putStringSet(
            CALIBRATION_KEY_PREFIX + sandwichIngredientCalibrationSettings.sandwichIngredient.id,
            serializeMovements(sandwichIngredientCalibrationSettings.movementSequence)
        )
        prefEditor.commit()
    }

    private fun serializeMovements(val movements: Set<SandwichIngredientMovement>): Set<String> {
    }


    private fun deserializeMovements(val movements: Set<String>): Set<SandwichIngredientMovement> {
    }

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }


    private fun getSharedPreferencesEditor(): SharedPreferences.Editor {
        return getSharedPreferences().edit()
    }

    companion object {
        private var instance: CalibrationSettingsRepository? = null
        fun getInstance(val context: Context): CalibrationSettingsRepository {
            val i = instance
            if (i != null) {
                return i
            }

            return synchronized(this) {
                val i2 = instance
                if (i2 != null) {
                    return i2
                } else {
                    instance = CalibrationSettingsRepository(context)
                    return instance
                }
            }
        }
    }
}