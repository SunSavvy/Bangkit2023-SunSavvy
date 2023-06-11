package com.bangkit.sunsavvy.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val username: MutableLiveData<String> = MutableLiveData()
    val uvIndex: MutableLiveData<Double> = MutableLiveData()
    val uvCategory: MutableLiveData<String> = MutableLiveData()
    val sunburnTime: MutableLiveData<String> = MutableLiveData()
    val skinType: MutableLiveData<Int> = MutableLiveData()
    val pa: MutableLiveData<Int> = MutableLiveData()
    val spf: MutableLiveData<Int> = MutableLiveData()
    val slogan: MutableLiveData<List<String>> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() {

//      TODO("Retrieve existed data, if data do not exist in database please leave it hardcoded as below")
        username.value = "Uzumaki Icha"
        uvIndex.value = 3.4
        skinType.value = 2
        pa.value = 3
        spf.value = 45

        uvCategory.value = when (uvIndex.value!!) {
            in 0.0..0.9 -> "Very Low UV Index"
            in 1.0..3.9 -> "Low UV Index"
            in 4.0..6.9 -> "Medium UV Index"
            in 7.0..9.9 -> "High UV Index"
            else -> "Extreme UV Index"
        }

        when (skinType.value) {
            in 0..1 -> {
                sunburnTime.value = when (uvIndex.value!!) {
                    in 0.0..0.9 -> "No risk of sunburn"
                    in 1.0..3.9 -> "Over 60 minutes"
                    in 4.0..6.9 -> "Around 30 minutes"
                    in 7.0..9.9 -> "Around 20 minutes"
                    else -> "Less than 15 minutes"
                }
            }
            else -> {
                sunburnTime.value = when (uvIndex.value!!) {
                    in 0.0..0.9 -> "No risk of sunburn"
                    in 1.0..3.9 -> "Over 60 minutes"
                    in 4.0..6.9 -> "Around 60 minutes"
                    in 7.0..9.9 -> "Around 40 minutes"
                    else -> "Less than 30 minutes"
                }
            }
        }

        val listSlogan = listOf(
            "slip",
            "slop",
            "slap",
            "seek",
            "slide"
        )
        slogan.value = listSlogan
    }
}