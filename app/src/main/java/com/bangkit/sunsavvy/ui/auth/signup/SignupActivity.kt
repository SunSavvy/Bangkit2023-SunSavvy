package com.bangkit.sunsavvy.ui.auth.signup

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.databinding.ActivitySignupBinding
import com.bangkit.sunsavvy.ui.auth.login.LoginActivity
import com.bangkit.sunsavvy.ui.auth.register.RegisterActivity
import com.bangkit.sunsavvy.utils.OnPressed
import com.bangkit.sunsavvy.utils.Animator
import com.bangkit.sunsavvy.utils.CustomDialog

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var signUpViewModel: SignUpViewModel

    private lateinit var cloudAnimators: List<Animator>
    private val speedMultipliers = listOf(0.1f, 0.3f, 0.5f)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            setupSignUp()
        }
        binding.login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        OnPressed().setButtonPressedPrimary(binding.btnSignUp)
        OnPressed().setTextPressedPrimary(binding.login, R.font.montserrat_semi_bold, R.font.montserrat_bold)

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

    private fun setupSignUp() {
        signUpViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[SignUpViewModel::class.java]

        val email = binding.inputEmail.text.toString().trim()
        val pass = binding.inputPassword.text.toString().trim()
        val passConf = binding.inputPasswordRetype.text.toString().trim()

        if (checkValidation(email, pass, passConf)) {
            CustomDialog.showLoading(this)

            if (email.isNotEmpty() && pass.isNotEmpty() && passConf.isNotEmpty()) {
                signUpViewModel.signUpresult.observe(this) { result ->

                    if (result != null) {
                        if (result.data != null) {
                            saveEmail(result.data.email)
                            startActivity(Intent(this@SignupActivity, RegisterActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, getString(R.string.view_content_empty), Toast.LENGTH_SHORT).show()
                            CustomDialog.hideLoading()
                        }
                    }
                }
            }
            signUpViewModel.signUp(email, pass)
        }
    }

    private fun checkValidation(
        email: String,
        password: String,
        passwordConfirm: String): Boolean {
        if (email.isEmpty()){
            binding.inputEmail.error = getString(R.string.error_email_empty)
            binding.inputEmail.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.inputEmail.error = getString(R.string.error_email_invalid)
            binding.inputEmail.requestFocus()
        } else if (password.isEmpty()){
            binding.inputPassword.error = getString(R.string.error_password_empty)
            binding.inputPassword.requestFocus()
        } else if (password != passwordConfirm) {
            binding.inputPasswordRetype.error = getString(R.string.error_password_wrong)
            binding.inputPasswordRetype.requestFocus()
        } else {
            return true
        }
        CustomDialog.hideLoading()
        return false
    }

    private fun saveEmail(email: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@SignupActivity)
            .edit()
            .putString("PREF_EMAIL", email)
            .apply()
    }
}