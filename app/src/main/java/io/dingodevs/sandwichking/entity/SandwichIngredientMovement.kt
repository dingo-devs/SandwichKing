package io.dingodevs.sandwichking.entity

import io.dingodevs.sandwichking.repository.ArduinoServo

data class SandwichIngredientMovement(val targetPosition: Int, val servo: ArduinoServo, val order: Int = 0) {
    override fun toString(): String {
        return "[${order}] ${servo.name} -> ${targetPosition})"
    }
}