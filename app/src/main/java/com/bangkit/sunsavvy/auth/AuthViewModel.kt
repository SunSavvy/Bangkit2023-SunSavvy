package com.bangkit.sunsavvy.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sunsavvy.data.model.UserPreferences

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    val registerSuccess = MutableLiveData<Boolean>()
    val registerToken = MutableLiveData<String?>()
    val loginSuccess = MutableLiveData<Boolean>()
    val loginToken = MutableLiveData<String?>()
    val updatePreferencesSuccess = MutableLiveData<Boolean>()

    fun register(username: String, password: String) {
        authRepository.register(username, password) { success, token ->
            registerSuccess.value = success
            registerToken.value = token
        }
    }

    fun login(username: String, password: String) {
        authRepository.login(username, password) { success, token ->
            loginSuccess.value = success
            loginToken.value = token
        }
    }

    fun updatePreferences(token: String, preferences: UserPreferences) {
        authRepository.updatePreferences(token, preferences) { success ->
            updatePreferencesSuccess.value = success
        }
    }
}
