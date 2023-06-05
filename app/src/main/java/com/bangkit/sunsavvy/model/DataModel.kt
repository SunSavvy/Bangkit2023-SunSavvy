package com.bangkit.sunsavvy.model

data class DataModel(
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val location: String? = null,
    val uvIndex: Int = 0,
    val uvCategory: String? = null,
    val timeToSunburn: Int = 0,
    val pa: Int = 0,
    val spf: Int = 0,
)
