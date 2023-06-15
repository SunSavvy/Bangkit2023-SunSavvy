package com.bangkit.sunsavvy.ui.auth.login

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.data.api.ApiConfig
import com.bangkit.sunsavvy.data.apimodel.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val userId: MutableLiveData<String> = MutableLiveData()

    val loginResult: MutableLiveData<LoginResponse> = MutableLiveData()

    fun loginUser(email: String, password: String) {
        val call: Call<LoginResponse> = ApiConfig.getApiService().login(email, password)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val signInResponse = response.body()
                    val userId = signInResponse?.data?.id
                    if (userId != null && response.isSuccessful) {
                        loginResult.postValue(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }
}
