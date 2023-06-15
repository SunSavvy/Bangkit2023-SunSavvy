package com.bangkit.sunsavvy.ui.auth.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.MainActivity
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.databinding.ActivityLoginBinding
import com.bangkit.sunsavvy.ui.auth.signup.SignupActivity
import com.bangkit.sunsavvy.utils.OnPressed
import com.bangkit.sunsavvy.utils.Animator
import com.bangkit.sunsavvy.utils.CustomDialog

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    private lateinit var cloudAnimators: List<Animator>
    private val speedMultipliers = listOf(0.1f, 0.3f, 0.5f)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            setUpLogin()
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

        OnPressed().setButtonPressedPrimary(binding.btnLogin)
        OnPressed().setTextPressedPrimary(binding.signUp, R.font.montserrat_semi_bold, R.font.montserrat_bold)

        animateCloud()
    }

    override fun onResume() {
        super.onResume()
        cloudAnimators.forEach { it.startAnimation() }
    }

    override fun onPause() {
        super.onPause()
        cloudAnimators.forEach { it.stopAnimation() }
    }

    override fun onDestroy() {
        super.onDestroy()
        cloudAnimators.forEach { it.stopAnimation() }
    }

    private fun animateCloud() {
        val clouds = listOf(binding.cloud1, binding.cloud2, binding.cloud3)
        cloudAnimators = clouds.zip(speedMultipliers).map { (cloud, speedMultiplier) -> Animator(cloud, resources.displayMetrics.widthPixels, speedMultiplier) }

        cloudAnimators.forEach { it.startAnimation() }
    }

    private fun checkValidation(
        email: String,
        password: String): Boolean {
        if (email.isEmpty()){
            binding.inputEmail.error = getString(R.string.error_email_empty)
            binding.inputEmail.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.inputEmail.error = getString(R.string.error_email_invalid)
            binding.inputEmail.requestFocus()
        } else if (password.isEmpty()){
            binding.inputPassword.error = getString(R.string.error_password_empty)
            binding.inputPassword.requestFocus()
        } else{
            return true
        }
        CustomDialog.hideLoading()
        return false
    }

    private fun setUpLogin() {
        loginViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[LoginViewModel::class.java]

        val email = binding.inputEmail.text.toString().trim()
        val pass = binding.inputPassword.text.toString().trim()

        if (checkValidation(email, pass)){
            CustomDialog.showLoading(this)

            loginViewModel.loginResult.observe(this) { result ->
                CustomDialog.hideLoading()

                if (result != null) {
                    saveToken(result.token)
                    saveSkin(result.data?.skintype)
                    saveName(result.data?.name)
                    saveResultEmail(result.data?.email)
                    saveResultName(result.data?.name)

                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    binding.inputPassword.error = getString(R.string.error_password_wrong)
                    binding.inputPassword.requestFocus()
                    CustomDialog.hideLoading()
                }
            }
            loginViewModel.loginUser(email, pass)
        }
    }

    private fun saveToken(token: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putString("PREF_TOKEN", token)
            .apply()
    }

    private fun saveSkin(token: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putString("PREF_SKIN", token)
            .apply()
    }

    private fun saveName(name: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putString("PREF_NAME", name)
            .apply()
    }

    private fun saveResultEmail(email: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putString("PREF_EMAIL_RESULT", email)
            .apply()
    }

    private fun saveResultName(name: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putString("PREF_NAME_RESULT", name)
            .apply()
    }
}