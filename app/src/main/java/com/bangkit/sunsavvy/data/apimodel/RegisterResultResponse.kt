package com.bangkit.sunsavvy.data.apimodel

import com.google.gson.annotations.SerializedName

data class RegisterResultResponse(

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("_id")
    val _id: String? = null,

    @field:SerializedName("__v")
    val __v: Int? = null,
)
