package com.bangkit.sunsavvy.data.apimodel

import com.google.gson.annotations.SerializedName

data class ResultResponseForecast(
    @field:SerializedName("predictions")
    val predictions: List<List<Double?>?>? = null
)