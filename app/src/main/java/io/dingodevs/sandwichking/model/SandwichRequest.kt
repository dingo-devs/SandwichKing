package io.dingodevs.sandwichking.model

data class SandwichRequest(val ingredients: Array<SandwichIngredient>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SandwichRequest

        if (!ingredients.contentEquals(other.ingredients)) return false

        return true
    }

    override fun hashCode(): Int {
        return ingredients.contentHashCode()
    }
}