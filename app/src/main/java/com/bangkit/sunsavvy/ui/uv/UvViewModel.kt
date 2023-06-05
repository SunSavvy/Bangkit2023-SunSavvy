package com.bangkit.sunsavvy.ui.uv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.model.InformationModel

class UvViewModel : ViewModel() {
    private val _items = MutableLiveData<List<InformationModel>>()
    val items: LiveData<List<InformationModel>> = _items

    init {
        val itemList = listOf(
            InformationModel("Head 1", "Body 1"),
            InformationModel("Head 2", "Body 2"),
            InformationModel("Head 3", "Body 3")
        )
        _items.value = itemList
    }
}