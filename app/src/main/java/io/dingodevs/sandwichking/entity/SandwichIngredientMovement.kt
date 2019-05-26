package io.dingodevs.sandwichking.entity

import io.dingodevs.sandwichking.repository.ArduinoServo

data class SandwichIngredientMovement(val servo: ArduinoServo, val targetPosition: Int = 90, var order: Int = 0) {
    override fun toString(): String {
        return "[${order}] ${servo.name} -> ${targetPosition})"
    }
}