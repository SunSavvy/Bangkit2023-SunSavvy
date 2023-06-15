package com.bangkit.sunsavvy.data.apimodel

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: RegisterResultResponse? = null,

    @field:SerializedName("token")
    val token: String? = null
)