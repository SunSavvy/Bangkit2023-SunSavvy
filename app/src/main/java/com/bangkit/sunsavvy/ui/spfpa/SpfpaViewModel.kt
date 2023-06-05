package com.bangkit.sunsavvy.ui.spfpa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SpfpaViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is SPF & PA Fragment"
    }
    val text: LiveData<String> = _text
}