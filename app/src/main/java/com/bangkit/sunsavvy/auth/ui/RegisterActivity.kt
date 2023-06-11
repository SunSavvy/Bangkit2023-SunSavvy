package com.bangkit.sunsavvy.auth.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.RequiresApi
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.databinding.ActivityRegisterBinding
import com.bangkit.sunsavvy.utils.Animator
import com.bangkit.sunsavvy.utils.OnPressed

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var selectedSkinType: String
    private lateinit var cloudAnimators: List<Animator>
    private val speedMultipliers = listOf(0.1f, 0.3f, 0.5f)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO("Set the registered data into user's profile. When success intent to MainActivity, if fail make a related toast -|- ID username, skin_type")

        // TODO("Skin type for uploaded as a part of user data")
        val skinType = selectedSkinType

        val items = listOf("Skin Type I", "Skin Type II", "Skin Type III", "Skin Type IV")
        val adapter = ArrayAdapter(this, R.layout.item_skintype, items)
        (binding.inputSkinType as? AutoCompleteTextView)?.setAdapter(adapter)

        (binding.inputSkinType as? AutoCompleteTextView)?.setOnItemClickListener { parent, view, position, id ->
            selectedSkinType = parent.getItemAtPosition(position) as? String ?: ""
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
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