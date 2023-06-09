package com.bangkit.sunsavvy.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.sunsavvy.data.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel constructor(private val userRepository: UserRepository) : ViewModel() {
    val getTheme: Flow<Boolean> = userRepository.getTheme()

    fun saveThemeSetting(darkModeState: Boolean) {
        viewModelScope.launch {
            userRepository.saveTheme(darkModeState)
        }
    }
}