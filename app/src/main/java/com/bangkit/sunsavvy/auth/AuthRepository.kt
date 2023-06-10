package com.bangkit.sunsavvy.auth

import com.bangkit.sunsavvy.data.api.ApiClient
import com.bangkit.sunsavvy.data.api.ApiService
import com.bangkit.sunsavvy.data.model.LoginRequest
import com.bangkit.sunsavvy.data.model.LoginResponse
import com.bangkit.sunsavvy.data.model.RegisterRequest
import com.bangkit.sunsavvy.data.model.RegisterResponse
import com.bangkit.sunsavvy.data.model.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {
    private val apiService: ApiService = ApiClient.apiService

    fun register(username: String, password: String, callback: (Boolean, String?) -> Unit) {
        val request = RegisterRequest(username, password)
        apiService.register(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    val token = registerResponse?.token
                    callback(true, token)
                } else {
                    callback(false, null)
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                callback(false, null)
            }
        })
    }

    fun login(username: String, password: String, callback: (Boolean, String?) -> Unit) {
        val request = LoginRequest(username, password)
        apiService.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    val token = loginResponse?.token
                    callback(true, token)
                } else {
                    callback(false, null)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(false, null)
            }
        })
    }

    fun updatePreferences(token: String, preferences: UserPreferences, callback: (Boolean) -> Unit) {
        apiService.updatePreferences("Bearer $token", preferences).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                callback(response.isSuccessful)
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callback(false)
            }
        })
    }
}
