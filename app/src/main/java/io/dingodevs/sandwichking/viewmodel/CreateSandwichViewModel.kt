package io.dingodevs.sandwichking.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import io.dingodevs.sandwichking.entity.SandwichRequest
import io.dingodevs.sandwichking.repository.SandwichIngredientId
import io.dingodevs.sandwichking.repository.SandwichIngredientImageUriRepository
import io.dingodevs.sandwichking.repository.SandwichIngredientRepository
import io.dingodevs.sandwichking.ui.model.SandwichIngredientModel

class CreateSandwichViewModel : ViewModel() {
    val sandwichIngredientsModelList by lazy {
        SandwichIngredientRepository.sandwichIngredientData.map {
            SandwichIngredientModel(
                it.id,
                it.name,
                SandwichIngredientImageUriRepository.sandwichIngredientImageUri(it.id),
                false
            )
        }
    }

    fun updateSelected(sandwichIngredientId: SandwichIngredientId, selected: Boolean) {
        sandwichIngredientsModelList.first { it.id == sandwichIngredientId }.selected = selected
    }

    // TODO: add callback
    fun createSandwich() {
        val sandwichRequest = SandwichRequest(sandwichIngredientsModelList.filter { it.selected }.map { it.id }.toSet())
        if (sandwichRequest.sandwichIngredientIds.isEmpty()) return

        // TODO: process request and send to arduino using bluetooth
        Log.i(this.toString(), sandwichRequest.sandwichIngredientIds.toString())
    }
}
