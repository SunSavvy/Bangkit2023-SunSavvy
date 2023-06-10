package com.bangkit.sunsavvy.data.model

data class RegisterRequest(
    val username: String,
    val password: String
)

data class RegisterResponse(
    val userId: String,
    val token: String
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val userId: String,
    val token: String
)

data class UserPreferences(
    val email: String,
    val name: String,
    val skinType: String,
    val theme: String,
    val alert: Boolean
)
