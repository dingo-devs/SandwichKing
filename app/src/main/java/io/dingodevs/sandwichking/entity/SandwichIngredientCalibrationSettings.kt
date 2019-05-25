package io.dingodevs.sandwichking.entity

import io.dingodevs.sandwichking.repository.ArduinoServo

class SandwichIngredientCalibrationSettings(
    val sandwichIngredient: SandwichIngredient,
    val initialServoPositions: Set<ArduinoServo, Int>
) {
    val movementSequence: Set<SandwichIngredientMovement>

    fun add(val movement: SandwichIngredientMovement) {
        movement.order = movementSequence.size
        movementSequence.add(movement)
    }

    fun setMovementSequence(val movements: List<SandwichIngredientMovement>) {
        movementSequence.clear()
        movements.forEach { add(it) }
    }

    fun popHead() {
        if (movementSequence.size > 0)
            movementSequence.removeAt(movementSequence.size - 1)
    }

    fun clear() {
        movementSequence.clear()
    }
}