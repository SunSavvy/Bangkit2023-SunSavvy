package com.bangkit.sunsavvy.ui.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.data.api.ApiConfig
import com.bangkit.sunsavvy.data.apimodel.ResponseForecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel : ViewModel() {

    val result = MutableLiveData<ResponseForecast>()

    fun getUV(id: String?){
        Log.i("my_token", "asd")
        if (id?.isNotEmpty() == true){
            ApiConfig.getApiService().takeUv(id, "papua",-8.499112,140.404984)
                .enqueue(object : Callback<ResponseForecast>{
                    override fun onResponse(
                        call: Call<ResponseForecast>,
                        response: Response<ResponseForecast>
                    ) {

                    }

                    override fun onFailure(call: Call<ResponseForecast>, t: Throwable) {
                        Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                    }
                })
        }
    }
}