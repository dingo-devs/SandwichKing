package io.dingodevs.sandwichking.ui.model

import io.dingodevs.sandwichking.repository.SandwichIngedientId

data class SandwichIngredientModel(
    val id: SandwichIngedientId,
    val name: String,
    val imageUri: String,
    var selected: Boolean
)