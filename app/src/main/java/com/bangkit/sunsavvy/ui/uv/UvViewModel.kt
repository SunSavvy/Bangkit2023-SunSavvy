package com.bangkit.sunsavvy.ui.uv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.data.localmodel.InformationModel
import com.bangkit.sunsavvy.data.localmodel.UvcategoryModel

class UvViewModel : ViewModel() {
    val information: MutableLiveData<List<InformationModel>> = MutableLiveData()
    val category: MutableLiveData<List<UvcategoryModel>> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() {
        information()
        category()
    }

    private fun information() {
        val itemList = listOf(
            InformationModel("UV", "UV synate consists of UV-A, UV-B, and UV-C rays. UV-C is completely absorbed by the atmosphere and does not reach the Earth's surface. UV-A and UV-B are the most relevant for human exposure"),
            InformationModel("UV-A", "UV-A flare can penetrate the deeper layers of the skin and play a role in skin aging, damaging collagen, and increasing the risk of skin cancer"),
            InformationModel("UV-B", "UV-B flare is a major cause of sunburn and also plays a role in skin cancer risk. Excessive exposure to UV-B can also lead to an increased risk of eye cataracts"),
            InformationModel("UV-C", "UV-C light is used in several industrial and medical applications, such as room sterilization, treatment of certain skin diseases, or infection control in certain environments. However, direct exposure to UV-C rays on the skin or eyes can cause serious damage and is best avoided"),
            InformationModel("UV Index", "UV Index is a scale of measurement of the intensity of ultraviolet (UV) radiation from the sun reaching the earth's surface. UV Indices are generally issued by local meteorological or environmental agencies and can be accessed through weather forecasts or authorized websites")
        )
        information.value = itemList
    }

    private fun category() {
        val itemList = listOf(
            UvcategoryModel(R.drawable.ic_uv_extreme, "10+", "Extreme", "Max Protection", "SPF 50+", R.drawable.ic_outfit, R.drawable.ic_sunscreen_outline, R.drawable.ic_hat, R.drawable.ic_umbrella, R.drawable.ic_sunglasses),
            UvcategoryModel(R.drawable.ic_uv_very_high, "7-9", "Very High", "Max Protection", "SPF 50+", R.drawable.ic_outfit, R.drawable.ic_sunscreen_outline, R.drawable.ic_hat, R.drawable.ic_umbrella, R.drawable.ic_sunglasses),
            UvcategoryModel(R.drawable.ic_uv_high, "4-6", "High", "High Protection", "SPF 30", R.drawable.ic_outfit, R.drawable.ic_sunscreen_outline, R.drawable.ic_hat, R.drawable.ic_umbrella, R.drawable.ic_sunglasses),
            UvcategoryModel(R.drawable.ic_uv_medium, "1-3", "Medium", "High Protection", "SPF 30", R.drawable.ic_outfit, R.drawable.ic_sunscreen_outline, R.drawable.ic_hat, R.drawable.ic_umbrella, R.drawable.ic_sunglasses),
            UvcategoryModel(R.drawable.ic_uv_low, "<1", "Low", "Min Protection", "SPF 30", R.drawable.ic_outfit, R.drawable.ic_sunscreen_outline, R.drawable.ic_hat)
        )
        category.value = itemList
    }
}