package io.dingodevs.sandwichking.repository

enum class ArduinoServo(val id: Int, val startRange: Int, val endRange: Int, val defaultPosition: Int) {
    Grip(1, 0, 179, 90),
    WristPitch(2, 0, 179, 90),
    WristRoll(3, 0, 179, 90),
    Elbow(4, 0, 179, 90),
    Shoulder(5, 0, 179, 90),
    Waist(6, 0, 179, 90)
}