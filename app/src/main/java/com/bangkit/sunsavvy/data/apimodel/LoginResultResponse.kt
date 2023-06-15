package com.bangkit.sunsavvy.data.apimodel

import com.google.gson.annotations.SerializedName

data class LoginResultResponse(

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("__v")
    val __v: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("skintype")
    val skintype: String? = null
)
