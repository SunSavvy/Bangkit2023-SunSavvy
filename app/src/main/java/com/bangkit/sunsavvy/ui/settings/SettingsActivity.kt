package com.bangkit.sunsavvy.ui.settings

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.CompoundButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.data.preferences.SettingPreferences
import com.bangkit.sunsavvy.data.preferences.ViewModelFactory
import com.bangkit.sunsavvy.data.preferences.dataStore
import com.bangkit.sunsavvy.databinding.ActivitySettingsBinding
import com.bangkit.sunsavvy.ui.auth.SplashActivity
import com.bangkit.sunsavvy.utils.GetColor
import com.bangkit.sunsavvy.utils.OnPressed

@Suppress("DEPRECATION")
class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var sharedPreferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSetting)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.action_settings)
        }

        val pref = SettingPreferences.getInstance(application.dataStore)
        val settingsViewModel = ViewModelProvider(this, ViewModelFactory(pref))[SettingsViewModel::class.java]

        settingsViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }

        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingsViewModel.saveThemeSetting(isChecked)
        }

        binding.btnLogOut.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.Dialog_Primary)
            builder.setTitle(getString(R.string.prompt_logout))
            builder.setMessage(getString(R.string.prompt_logout_check))
            builder.setPositiveButton(getString(R.string.prompt_logout_logout)) { _, _ ->
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
                sharedPreferences.edit().remove("PREF_TOKEN").apply()
                sharedPreferences.edit().remove("PREF_NAME").apply()
                sharedPreferences.edit().remove("PREF_SKIN").apply()
                sharedPreferences.edit().remove("PREF_EMAIL").apply()
                val intent = Intent(this, SplashActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
            builder.setNegativeButton(getString(R.string.prompt_logout_cancel)) { _, _ -> }
            builder.show()
        }
        OnPressed().setButtonPressedSecondary(binding.btnLogOut,
            GetColor.getPrimaryColor(this),
            GetColor.getErrorColor(this)
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
