package com.bangkit.sunsavvy.data

import kotlinx.coroutines.flow.Flow

class UserRepository constructor(private val preferences: AppPreferences) {
    fun getTheme(): Flow<Boolean> = preferences.getThemeSetting()

    suspend fun saveTheme(isDarkModeActive: Boolean) {
        preferences.saveThemeSetting(isDarkModeActive)
    }
}