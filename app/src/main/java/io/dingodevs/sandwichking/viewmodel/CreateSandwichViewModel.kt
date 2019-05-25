package io.dingodevs.sandwichking.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import io.dingodevs.sandwichking.entity.SandwichRequest
import io.dingodevs.sandwichking.repository.SandwichIngredientImageUriRepository
import io.dingodevs.sandwichking.repository.SandwichIngredientRepository
import io.dingodevs.sandwichking.ui.model.SandwichIngredientModel

class CreateSandwichViewModel : ViewModel() {
    val sandwichIngredientsModelMap by lazy {
        SandwichIngredientRepository.sandwichIngredientData.associateBy({ it.id }, {
            SandwichIngredientModel(
                it.id,
                it.name,
                SandwichIngredientImageUriRepository.sandwichIngredientImageUri(it.id),
                false
            )
        })
    }

    fun sandwichIngredientsModelList(): List<SandwichIngredientModel> = sandwichIngredientsModelMap.values.toList()

    // TODO: add callback
    fun createSandwich() {
        val sandwichRequest = SandwichRequest(sandwichIngredientsModelMap.filter { it.value.selected }.keys)
        if (sandwichRequest.sandwichIngedientIds.isEmpty()) return

        // TODO: process request and send to arduino using bluetooth
        Log.i(this.toString(), sandwichRequest.sandwichIngedientIds.toString())
    }
}
