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
    private val adminArea = MutableLiveData<String>()
    private val latitude = MutableLiveData<String>()
    private val longitude = MutableLiveData<String>()
    val result = MutableLiveData<ResponseForecast?>()

    fun setAdminArea(value: String) {
        adminArea.value = value
    }

    fun setLatitude(value: Double) {
        latitude.value = value.toString()
    }

    fun setLongitude(value: Double) {
        longitude.value = value.toString()
    }

    fun getUV(id: String?) {
        Log.i("my_token", "asd")
        if (id?.isNotEmpty() == true) {
            val adminAreaValue = adminArea.value
            val latitudeValue = latitude.value
            val longitudeValue = longitude.value

            if (adminAreaValue != null && latitudeValue != null && longitudeValue != null) {
                ApiConfig.getApiService().takeUv(id, adminAreaValue, latitudeValue.toDouble(), longitudeValue.toDouble())
                    .enqueue(object : Callback<ResponseForecast> {
                        override fun onResponse(
                            call: Call<ResponseForecast>,
                            response: Response<ResponseForecast>
                        ) {
                            if (response.isSuccessful) {
                                val forecast = response.body()
                                result.value = forecast
                            } else {
                                Log.e(ContentValues.TAG, "API call failed: ${response.code()}")
                            }
                        }

                        override fun onFailure(call: Call<ResponseForecast>, t: Throwable) {
                            Log.e(ContentValues.TAG, "API call failed: ${t.message.toString()}")
                        }
                    })
            }
        }
    }
}