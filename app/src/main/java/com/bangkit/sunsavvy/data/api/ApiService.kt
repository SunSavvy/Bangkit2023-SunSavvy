package com.bangkit.sunsavvy.data.api

import com.bangkit.sunsavvy.data.model.LoginRequest
import com.bangkit.sunsavvy.data.model.LoginResponse
import com.bangkit.sunsavvy.data.model.RegisterRequest
import com.bangkit.sunsavvy.data.model.RegisterResponse
import com.bangkit.sunsavvy.data.model.UserPreferences
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @POST("/api/auth/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("/api/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @PUT("/api/auth/preference")
    fun updatePreferences(@Header("Authorization") token: String, @Body preferences: UserPreferences): Call<Unit>
}