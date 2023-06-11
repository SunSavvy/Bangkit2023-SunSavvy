package com.bangkit.sunsavvy.auth.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bangkit.sunsavvy.MainActivity
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.databinding.ActivityLoginBinding
import com.bangkit.sunsavvy.utils.OnPressed
import com.bangkit.sunsavvy.utils.Animator

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var cloudAnimators: List<Animator>
    private val speedMultipliers = listOf(0.1f, 0.3f, 0.5f)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO("If login success, intent to MainActivity, when fail make a related toast -|- ID: input_email, input_password")

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
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
}