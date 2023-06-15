package com.bangkit.sunsavvy.ui.skintype

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.data.localmodel.SkintypeModel

class SkintypeViewModel : ViewModel() {
    val category: MutableLiveData<List<SkintypeModel>> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() {
        val itemList = listOf(
            SkintypeModel(R.drawable.img_person_type_1, "Skin Type I", "very light skin\nblond/red hair\nblue/green eyes", "I", "Prone to flammability and rarely or never darken color"),
            SkintypeModel(R.drawable.img_person_type_2, "Skin Type II", "light skin\nlight/golden brown hair\nbrown/green eyes", "II", "Usually burns first before slowly darkening the color"),
            SkintypeModel(R.drawable.img_person_type_3, "Skin Type III", "yellowish/mature skin\ndark brown/black hair\nbrown/black eyes", "III", "Usually burns first before darkening the color"),
            SkintypeModel(R.drawable.img_person_type_4, "Skin Type IV", "mature/brown skin\ndark black/blond hair\nbrown/black eyes", "IV", "Rarely burns but can darken color with sufficient sun exposure"),
        )
        category.value = itemList
    }
}