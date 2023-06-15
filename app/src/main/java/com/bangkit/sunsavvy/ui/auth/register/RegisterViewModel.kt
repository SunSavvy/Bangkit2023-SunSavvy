package com.bangkit.sunsavvy.ui.auth.register

import android.app.Application
import android.content.ContentValues
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bangkit.sunsavvy.data.api.ApiConfig
import com.bangkit.sunsavvy.data.apimodel.PreferenceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    val registrationResult = MutableLiveData<PreferenceResponse?>()

    private val email =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_EMAIL", "") ?: ""

    fun registerUser(name: String, skintype: String) {
        registrationResult.value = null
        Log.d("MSGMODEL", "pesan = {$email}")
        val call = ApiConfig.getApiService().register(email, name, skintype)

        call.enqueue(object : Callback<PreferenceResponse> {
            override fun onResponse(
                call: Call<PreferenceResponse>,
                response: Response<PreferenceResponse>
            ) {
                if (response.isSuccessful) {
                    registrationResult.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<PreferenceResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }
}

