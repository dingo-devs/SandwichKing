package io.dingodevs.sandwichking.repository

enum class SandwichIngedientId(val id: Int) {
    Tomato(1 shl 0),
    Pickle(1 shl 1),
    Lettuce(1 shl 2),
    Onion(1 shl 3),
    Burger(1 shl 4)
}