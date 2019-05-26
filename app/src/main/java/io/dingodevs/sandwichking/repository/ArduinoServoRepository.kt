package io.dingodevs.sandwichking.repository

object ArduinoServoRepository {
    fun getById(id: Int): ArduinoServo {
        return ArduinoServo.values().filter { it.id == id }.first()
    }
}