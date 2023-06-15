package com.bangkit.sunsavvy.ui.auth.signup

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.data.api.ApiConfig
import com.bangkit.sunsavvy.data.apimodel.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    val signUpresult: MutableLiveData<RegisterResponse?> = MutableLiveData()

    fun signUp(email: String, name: String) {
        signUpresult.value = null
        val call = ApiConfig.getApiService().SignUp(email, name)

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    signUpresult.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }
}