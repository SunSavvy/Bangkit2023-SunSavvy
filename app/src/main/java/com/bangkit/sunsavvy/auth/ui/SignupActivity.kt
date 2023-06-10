package com.bangkit.sunsavvy.auth.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.auth.AuthViewModel
import com.bangkit.sunsavvy.databinding.ActivitySignupBinding
import com.bangkit.sunsavvy.utils.OnPressed
import com.bangkit.sunsavvy.utils.Animator

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: AuthViewModel

    private lateinit var cloudAnimators: List<Animator>
    private val speedMultipliers = listOf(0.1f, 0.3f, 0.5f)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        viewModel.registerSuccess.observe(this, Observer { success ->
            if (success) {
                val token = viewModel.registerToken.value
                // Handle successful registration
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Handle registration failure
                Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnSignUp.setOnClickListener {
            val username = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
            val retypePassword = binding.inputPasswordRetype.text.toString()

            if (password == retypePassword) {
                viewModel.register(username, password)
            } else {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
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
}