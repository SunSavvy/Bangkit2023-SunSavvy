package com.bangkit.sunsavvy.data.apimodel

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: LoginResultResponse? = null,

    @field:SerializedName("token")
    val token: String? = null
)