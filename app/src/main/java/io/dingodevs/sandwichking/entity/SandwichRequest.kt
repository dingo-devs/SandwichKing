package io.dingodevs.sandwichking.entity

import io.dingodevs.sandwichking.repository.SandwichIngredientId

data class SandwichRequest(val sandwichIngredientIds: Set<SandwichIngredientId>)