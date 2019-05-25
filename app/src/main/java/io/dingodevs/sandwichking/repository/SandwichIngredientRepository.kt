package io.dingodevs.sandwichking.repository

import io.dingodevs.sandwichking.entity.SandwichIngredient

object SandwichIngredientRepository {
    val sandwichIngredientData = listOf(
        SandwichIngredient(SandwichIngedientId.Tomato, "Tomato"),
        SandwichIngredient(SandwichIngedientId.Pickle, "Pickle"),
        SandwichIngredient(SandwichIngedientId.Lettuce, "Lettuce"),
        SandwichIngredient(SandwichIngedientId.Onion, "Onion"),
        SandwichIngredient(SandwichIngedientId.Burger, "Burger")
    )
}