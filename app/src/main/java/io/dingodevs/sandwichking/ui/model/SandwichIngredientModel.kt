package io.dingodevs.sandwichking.ui.model

import io.dingodevs.sandwichking.repository.SandwichIngredientId

data class SandwichIngredientModel(
    val id: SandwichIngredientId,
    val name: String,
    val imageUri: String,
    var selected: Boolean
)