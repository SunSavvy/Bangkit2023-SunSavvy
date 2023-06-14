package com.bangkit.sunsavvy.auth.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import com.bangkit.sunsavvy.MainActivity
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.databinding.ActivityRegisterBinding
import com.bangkit.sunsavvy.ui.skintype.SkintypeActivity
import com.bangkit.sunsavvy.utils.Animator
import com.bangkit.sunsavvy.utils.OnPressed

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var selectedSkinType: String
    private var skinType: Int = 0
    private lateinit var cloudAnimators: List<Animator>
    private val speedMultipliers = listOf(0.1f, 0.3f, 0.5f)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO("Set the registered data into user's profile. When success intent to MainActivity, if fail make a related toast -|- ID username, skin_type")

        val items = listOf("Skin Type I", "Skin Type II", "Skin Type III", "Skin Type IV")
        val adapter = ArrayAdapter(this, R.layout.spinner_skintype, items)
        val autoCompleteTextView = binding.inputSkinType as? AutoCompleteTextView
        autoCompleteTextView?.setAdapter(adapter)

        autoCompleteTextView?.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as? String
            if (selectedItem != null) {
                // TODO("Skin type for uploaded as a part of user data")arent.getItemAtPosition(position) as? String
                when (selectedItem) {
                    "Skin Type I" -> skinType = 1
                    "Skin Type II" -> skinType = 2
                    "Skin Type III" -> skinType = 3
                    "Skin Type IV" -> skinType = 4
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.skinTypeMore.setOnClickListener {
            val intent = Intent(this, SkintypeActivity::class.java)
            startActivity(intent)
        }

        OnPressed().setButtonPressedPrimary(binding.btnRegister)
        OnPressed().setTextPressedPrimary(binding.skinTypeMore, R.font.montserrat_semi_bold, R.font.montserrat_bold)

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