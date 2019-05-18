package io.dingodevs.sandwichking.repository

import io.dingodevs.sandwichking.R

object SandwichIngredientImageUriRepository {
    private val RESOURCE_BASE = "android.resource://io.dingodevs.sandwichking/"

    private val imageUriMap = mapOf(
        1 to RESOURCE_BASE + R.drawable.img_ingredient_tomatoes,
        2 to RESOURCE_BASE + R.drawable.img_ingredient_pickles,
        3 to RESOURCE_BASE + R.drawable.img_ingredient_lettuce,
        4 to RESOURCE_BASE + R.drawable.img_ingredient_onion,
        5 to RESOURCE_BASE + R.drawable.img_ingredient_burger
    )

    fun sandwichIngredientImageUri(sandwichIngredientId: Int): String =
        imageUriMap[sandwichIngredientId].orEmpty()
}