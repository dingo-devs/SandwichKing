package io.dingodevs.sandwichking.repository

import io.dingodevs.sandwichking.R

object SandwichIngredientImageUriRepository {
    private const val RESOURCE_BASE = "android.resource://io.dingodevs.sandwichking/"

    private val imageUriMap = mapOf(
        SandwichIngedientId.Tomato to RESOURCE_BASE + R.drawable.img_ingredient_tomatoes,
        SandwichIngedientId.Pickle to RESOURCE_BASE + R.drawable.img_ingredient_pickles,
        SandwichIngedientId.Lettuce to RESOURCE_BASE + R.drawable.img_ingredient_lettuce,
        SandwichIngedientId.Onion to RESOURCE_BASE + R.drawable.img_ingredient_onion,
        SandwichIngedientId.Burger to RESOURCE_BASE + R.drawable.img_ingredient_burger
    )

    fun sandwichIngredientImageUri(sandwichIngredientId: SandwichIngedientId): String =
        imageUriMap[sandwichIngredientId].orEmpty()
}