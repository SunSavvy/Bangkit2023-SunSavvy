package com.bangkit.sunsavvy.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    val username: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val skinType: MutableLiveData<Int> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() {
//        username.value = "Uzumaki Icha"
//        email.value = "kyubi.buntung@rawr.com"
//        skinType.value = 2
    }
}