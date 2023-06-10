package com.bangkit.sunsavvy.auth.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.MainActivity
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.auth.AuthViewModel
import com.bangkit.sunsavvy.data.model.UserPreferences
import com.bangkit.sunsavvy.databinding.ActivityRegisterBinding
import com.bangkit.sunsavvy.utils.Animator
import com.bangkit.sunsavvy.utils.OnPressed

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    private lateinit var selectedSkinType: String
    private lateinit var cloudAnimators: List<Animator>
    private val speedMultipliers = listOf(0.1f, 0.3f, 0.5f)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        viewModel.updatePreferencesSuccess.observe(this, Observer { success ->
            if (success) {
                // Handle successful preferences update
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Handle preferences update failure
                Toast.makeText(this, "Failed to update preferences. Please try again.", Toast.LENGTH_SHORT).show()
            }
        })

        val items = listOf("Skin Type I", "Skin Type II", "Skin Type III", "Skin Type IV")
        val adapter = ArrayAdapter(this, R.layout.item_skintype, items)
        (binding.inputSkinType as? AutoCompleteTextView)?.setAdapter(adapter)

        (binding.inputSkinType as? AutoCompleteTextView)?.setOnItemClickListener { parent, view, position, id ->
            selectedSkinType = parent.getItemAtPosition(position) as? String ?: ""
        }

        binding.btnRegister.setOnClickListener {
            val skinType = selectedSkinType
            val preferences = UserPreferences("", "", skinType, "", false)
            val token = viewModel.registerToken.value
            if (token != null) {
                viewModel.updatePreferences(token, preferences)
            }
        }

        OnPressed().setButtonPressedPrimary(binding.btnRegister)

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