package io.dingodevs.sandwichking.entity

class SandwichIngredientCalibrationSettings(
    val sandwichIngredient: SandwichIngredient
) {
    val movementSequence: MutableList<SandwichIngredientMovement> = mutableListOf()

    fun add(movement: SandwichIngredientMovement) {
        movement.order = movementSequence.size
        movementSequence.add(movement)
    }

    fun setMovementSequence(movements: List<SandwichIngredientMovement>) {
        clear()
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