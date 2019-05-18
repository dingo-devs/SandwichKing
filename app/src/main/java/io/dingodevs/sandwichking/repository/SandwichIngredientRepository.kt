package io.dingodevs.sandwichking.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.dingodevs.sandwichking.model.SandwichIngredient

object SandwichIngredientRepository {
    private val sandwichIngredientData = MutableLiveData<List<SandwichIngredient>>()

    init {
        sandwichIngredientData.value = listOf(
            SandwichIngredient(1, "Tomato"),
            SandwichIngredient(2, "Pickle"),
            SandwichIngredient(3, "Lettuce"),
            SandwichIngredient(4, "Onion"),
            SandwichIngredient(5, "Burger")
        )
    }

    fun findSandwichIngredients(): LiveData<List<SandwichIngredient>> = sandwichIngredientData
}