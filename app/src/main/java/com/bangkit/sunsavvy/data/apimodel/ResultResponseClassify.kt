package com.bangkit.sunsavvy.data.apimodel

import com.google.gson.annotations.SerializedName

data class ResultResponseClassify(

    @field:SerializedName("skinType")
    val skinType: String? = null,

    @field:SerializedName("uvIndex")
    val uvIndex: String? = null,

    @field:SerializedName("spf")
    val spf: Int? = null,
)