package com.bangkit.sunsavvy.data.apimodel

import com.google.gson.annotations.SerializedName

data class ResponseClassify(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: List<ResultResponseClassify?>? = null,
)