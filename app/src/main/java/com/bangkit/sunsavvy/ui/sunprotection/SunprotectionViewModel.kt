package com.bangkit.sunsavvy.ui.sunprotection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.data.localmodel.SunprotectionModel

class SunprotectionViewModel : ViewModel() {
    val category: MutableLiveData<List<SunprotectionModel>> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() {
        val itemList = listOf(
            SunprotectionModel(R.drawable.img_outfit, "Slip", "Wear protective clothing"),
            SunprotectionModel(R.drawable.img_sunscreen, "Slop", "Appy sunscreen"),
            SunprotectionModel(R.drawable.img_hat, "Slap", "Wear a hat"),
            SunprotectionModel(R.drawable.img_umbrella, "Seek", "Find a shade"),
            SunprotectionModel(R.drawable.img_sunglasses, "Slide", "Use sunglasses"),
        )
        category.value = itemList
    }
}