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
        username.value = "Uzumaki Icha"
        uvIndex.value = 3.4
        uvCategory.value = "Low UV Index"
        sunburnTime.value = "27 Minutes"
        skinType.value = 2
        pa.value = 3
        spf.value = 45

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