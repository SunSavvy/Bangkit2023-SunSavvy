package com.bangkit.sunsavvy.ui.spfpa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.data.localmodel.InformationModel
import com.bangkit.sunsavvy.data.localmodel.SpfpaModel

class SpfpaViewModel : ViewModel() {
    val information: MutableLiveData<List<InformationModel>> = MutableLiveData()
    val category: MutableLiveData<List<SpfpaModel>> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() {
        information()
        sunProtection()
    }

    private fun information() {
        val itemList = listOf(
            InformationModel("SPF", "SPF (Sun Protection Factor) is a measure used to determine the extent to which sunscreen protects the skin from UVB rays"),
            InformationModel("PA", "PA++ indicates a level of protection against UVA rays, where the more \"+\" marks there are, the higher the level of protection")
        )
        information.value = itemList
    }

    private fun sunProtection() {
        val itemList = listOf(
            SpfpaModel("50+", "PA++++", "Suitable for situations where sun exposure is extreme, such as being in an area with intense sunlight or while doing intense outdoor sports"),
            SpfpaModel("50", "PA+++", "Recommended for strong sun exposure, such as sunbathing on the beach or doing activities in direct sunlight"),
            SpfpaModel("30", "PA+++", "Recommended for use in outdoor activities with moderate sun exposure"),
            SpfpaModel("15", "PA++", "Suitable for daily activities indoors or minimal sun exposure")
        )
        category.value = itemList
    }
}