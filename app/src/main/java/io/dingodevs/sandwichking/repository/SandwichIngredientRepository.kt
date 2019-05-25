package io.dingodevs.sandwichking.repository

import io.dingodevs.sandwichking.entity.SandwichIngredient

object SandwichIngredientRepository {
    val sandwichIngredientData = listOf(
        SandwichIngredient(SandwichIngredientId.Tomato, "Tomato"),
        SandwichIngredient(SandwichIngredientId.Pickle, "Pickle"),
        SandwichIngredient(SandwichIngredientId.Lettuce, "Lettuce"),
        SandwichIngredient(SandwichIngredientId.Onion, "Onion"),
        SandwichIngredient(SandwichIngredientId.Burger, "Burger")
    )
}