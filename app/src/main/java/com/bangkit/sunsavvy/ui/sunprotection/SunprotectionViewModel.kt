package com.bangkit.sunsavvy.ui.sunprotection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SunprotectionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Sun Protection Fragment"
    }
    val text: LiveData<String> = _text
}