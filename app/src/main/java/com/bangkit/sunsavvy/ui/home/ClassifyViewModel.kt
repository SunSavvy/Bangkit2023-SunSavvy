package com.bangkit.sunsavvy.ui.home

import android.app.Application
import android.content.ContentValues
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bangkit.sunsavvy.data.api.ApiConfig
import com.bangkit.sunsavvy.data.apimodel.ResponseClassify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassifyViewModel (application: Application) : AndroidViewModel(application) {

    val result = MutableLiveData<ResponseClassify>()

    private val id =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_TOKEN", "")

    private val skin =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_SKIN", "")

    private val index =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_SKIN", "")

    fun getSUV(){
        if (id?.isNotEmpty() == true && skin != null && index != null){
            ApiConfig.getApiService().takeSpf(id, skin, index)
                .enqueue(object : Callback<ResponseClassify>{
                    override fun onResponse(
                        call: Call<ResponseClassify>,
                        response: Response<ResponseClassify>
                    ) {
                        Log.d("pesan", "hasil = {$id dan $skin dan $index}")
                        if (response.isSuccessful){
                            result.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<ResponseClassify>, t: Throwable) {
                        Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                    }
                })
        }
    }
}







