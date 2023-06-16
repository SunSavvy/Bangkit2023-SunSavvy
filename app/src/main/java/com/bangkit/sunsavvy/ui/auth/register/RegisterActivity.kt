package com.bangkit.sunsavvy.ui.auth.register

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.MainActivity
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.databinding.ActivityRegisterBinding
import com.bangkit.sunsavvy.ui.auth.login.LoginActivity
import com.bangkit.sunsavvy.ui.skintype.SkintypeActivity
import com.bangkit.sunsavvy.utils.Animator
import com.bangkit.sunsavvy.utils.OnPressed

@Suppress("DEPRECATION")
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var sharedPreferences: SharedPreferences

    private var resultName: String? = null
    private var resultEmail : String? = null
    private var resultSkin : String? = null

    private lateinit var cloudAnimators: List<Animator>
    private val speedMultipliers = listOf(0.1f, 0.3f, 0.5f)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        resultName = sharedPreferences.getString("PREF_NAME_RESULT", "")
        resultEmail = sharedPreferences.getString("PREF_EMAIL_RESULT", "")
        resultSkin = sharedPreferences.getString("PREF_SKIN_RESULT", "")

        val token = PreferenceManager.getDefaultSharedPreferences(this).getString("PREF_TOKEN", null)

        if (token != null){
            binding.titleRegister.text = getString(R.string.view_update_profile)
            binding.btnRegister.text = getString(R.string.view_update_profile)
            binding.inputUsername.setText(resultName)
            binding.inputUsername.setText(resultSkin)
            binding.btnRegister.setOnClickListener {
                registerUser()
                Toast.makeText(this, getString(R.string.view_profile_updated), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
            }
        }else{
            binding.btnRegister.setOnClickListener {
                registerUser()
                Toast.makeText(this, getString(R.string.view_profile_created), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }

        val items = listOf("Skin Type I", "Skin Type II", "Skin Type III", "Skin Type IV")
        val adapter = ArrayAdapter(this, R.layout.spinner_skintype, items)
        (binding.inputSkinType as? AutoCompleteTextView)?.setAdapter(adapter)

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

    private fun registerUser() {
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        val name = binding.inputUsername.text.toString().trim()

        val skintype: String = when ((binding.inputSkinType as? AutoCompleteTextView)?.text.toString()) {
            "Skin Type I" -> "1"
            "Skin Type II" ->"2"
            "Skin Type III" -> "3"
            "Skin Type IV" -> "4"
            else -> "1"
        }

        if (name.isNotEmpty() && skintype.isNotEmpty()) {
            registerViewModel.registerUser(name, skintype)
        }

        registerViewModel.registrationResult.observe(this@RegisterActivity) { result ->
            if(result != null){
                saveName(result.data?.name)
                saveName(result.data?.skintype)
            }
        }
    }

    private fun saveName(name: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@RegisterActivity)
            .edit()
            .putString("PREF_NAME", name)
            .apply()
    }
}