package com.bangkit.sunsavvy.ui.skintype

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SkintypeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Skin Type Fragment"
    }
    val text: LiveData<String> = _text
}