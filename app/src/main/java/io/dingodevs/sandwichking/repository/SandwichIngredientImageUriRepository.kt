package io.dingodevs.sandwichking.repository

import io.dingodevs.sandwichking.R

object SandwichIngredientImageUriRepository {
    private const val RESOURCE_BASE = "android.resource://io.dingodevs.sandwichking/"

    private val imageUriMap = mapOf(
        SandwichIngredientId.Tomato to RESOURCE_BASE + R.drawable.img_ingredient_tomatoes,
        SandwichIngredientId.Pickle to RESOURCE_BASE + R.drawable.img_ingredient_pickles,
        SandwichIngredientId.Lettuce to RESOURCE_BASE + R.drawable.img_ingredient_lettuce,
        SandwichIngredientId.Onion to RESOURCE_BASE + R.drawable.img_ingredient_onion,
        SandwichIngredientId.Burger to RESOURCE_BASE + R.drawable.img_ingredient_burger
    )

    fun sandwichIngredientImageUri(sandwichIngredientId: SandwichIngredientId): String =
        imageUriMap[sandwichIngredientId].orEmpty()
}