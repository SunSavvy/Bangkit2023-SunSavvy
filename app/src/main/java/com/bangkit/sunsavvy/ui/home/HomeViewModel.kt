package com.bangkit.sunsavvy.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val slogan: MutableLiveData<List<String>> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() {
        title.value = "Hi, Yo!"

        val listSlogan = listOf("slip", "slop", "slap", "seek", "slide")
        slogan.value = listSlogan
    }
}