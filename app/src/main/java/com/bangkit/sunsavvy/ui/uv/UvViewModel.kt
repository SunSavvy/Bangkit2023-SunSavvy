package com.bangkit.sunsavvy.ui.uv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.data.model.InformationModel

class UvViewModel : ViewModel() {
    val information: MutableLiveData<List<InformationModel>> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() {
        val itemList = listOf(
            InformationModel("Head 1", "Body 1"),
            InformationModel("Head 2", "Body 2"),
            InformationModel("Head 3", "Body 3")
        )
        information.value = itemList
    }
}