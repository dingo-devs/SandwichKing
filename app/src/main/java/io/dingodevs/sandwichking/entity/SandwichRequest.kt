package io.dingodevs.sandwichking.entity

import io.dingodevs.sandwichking.repository.SandwichIngedientId

data class SandwichRequest(val sandwichIngedientIds: Set<SandwichIngedientId>)