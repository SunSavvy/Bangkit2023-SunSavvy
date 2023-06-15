package com.bangkit.sunsavvy.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.sunsavvy.data.preferences.SettingPreferences
import kotlinx.coroutines.launch

class SettingsViewModel(private val pref: SettingPreferences) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }

    fun getAlertSettings(): LiveData<Boolean> {
        return pref.getAlertSetting().asLiveData()
    }

    fun saveAlertSetting(isAlertActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isAlertActive)
        }
    }
}