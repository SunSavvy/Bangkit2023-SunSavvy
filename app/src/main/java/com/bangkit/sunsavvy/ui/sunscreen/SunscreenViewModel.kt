package com.bangkit.sunsavvy.ui.sunscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.data.localmodel.InformationModel

class SunscreenViewModel : ViewModel() {
    val information: MutableLiveData<List<InformationModel>> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() {
        val itemList = listOf(
            InformationModel("Physical Sunscreen", "Works by blocking and reflecting UV rays  from the skin"),
            InformationModel("Chemical Sunscreen", "Works by absorbing sunlight and converting it into non-harmful energy"),
            InformationModel("Mineral Sunscreen", "Works by reflecting sunlight from the skin"),
            InformationModel("Water Based Sunscreen", "Tends to be lighter, absorbs quickly, and is suitable for oily or acne-prone skin"),
            InformationModel("Water Resistant Sunscreen", "Protects the skin when exposed to water or sweat"),
            InformationModel("Tinted Sunscreen", "Helps disguise blemishes or even out the appearance of the skin"),
            InformationModel("High SPF Sunscreen", "Provides higher protection against sunlight"),
            InformationModel("Broad Spectrum Sunscreen", "Protects the skin from UVA and UVB rays"),
            InformationModel("PA Rated Sunscreen", "Contains UVA protection expressed by the PA (Protection Grade of UVA) rating system"),
            InformationModel("Sunscreen with SPF Lip", "Specifically to protect lips from the sun with SPF stated on the packaging.")
        )
        information.value = itemList
    }
}