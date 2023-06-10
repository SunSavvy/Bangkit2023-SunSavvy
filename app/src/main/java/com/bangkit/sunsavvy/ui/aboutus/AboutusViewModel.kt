package com.bangkit.sunsavvy.ui.aboutus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.data.model.AboutusModel

class AboutusViewModel : ViewModel() {
    val items: MutableLiveData<List<AboutusModel>> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() {
        val itemList = listOf(
            AboutusModel(
                R.drawable.img_profile_maristy,
                "Maristy Widya Pangestika",
                "maristywidya@gmail.com",
                "Machine Learning",
                R.drawable.ic_social_instagram,
                "https://instagram.com/wmaristy",
                R.drawable.ic_social_github,
                "https://github.com/maristyw",
                R.drawable.ic_social_linkedin,
                "https://www.linkedin.com/in/maristy-widya-p-b964991b3"
            ),
            AboutusModel(
                R.drawable.img_profile_angelina,
                "Angelina Chandra",
                "angelchandra27@gmail.com",
                "Cloud Computing",
                R.drawable.ic_social_instagram,
                "https://www.instagram.com/angel.chandra",
                R.drawable.ic_social_github,
                "https://github.com/angelinachandra",
                R.drawable.ic_social_linkedin,
                "https://www.linkedin.com/in/angelinachandra/"
            ),
            AboutusModel(
                R.drawable.img_profile_gagah,
                "Gagah Aji Gunadi",
                "aggagah.dev@gmail.com",
                "Cloud Computing",
                R.drawable.ic_social_instagram,
                "https://instagram.com/ag.gagah",
                R.drawable.ic_social_github,
                "https://github.com/aggagah",
                R.drawable.ic_social_linkedin,
                "https://linkedin.com/in/gagah-aji-gunadi",
                R.drawable.ic_social_website,
                "https://aggagah.my.id"
            ),
            AboutusModel(
                R.drawable.img_profile_yodhi,
                "Yodhi Anugrah Damar Saputra",
                "yodhianugrah23@gmail.com",
                "Mobile Development",
                R.drawable.ic_social_instagram,
                "https://www.instagram.com/ihd.yo",
                R.drawable.ic_social_github,
                "https://github.com/ihdyo",
                R.drawable.ic_social_linkedin,
                "https://www.linkedin.com/in/yodhi-anugrah"
            ),
        )
        items.value = itemList
    }
}