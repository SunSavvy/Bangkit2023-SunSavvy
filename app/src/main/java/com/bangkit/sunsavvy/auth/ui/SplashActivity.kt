package com.bangkit.sunsavvy.auth.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.databinding.ActivitySplashBinding

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO("Check if user logged in. If true intent to MainActivity, if false intent to LandingActivity")

        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        val versionName = packageInfo.versionName

        binding.appVersion.text = "Version $versionName"

        Handler().postDelayed({
            val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo

            if (activeNetwork?.isConnected == true) {
                val intent = Intent(this, LandingActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.prompt_connection_no))
                builder.setMessage(getString(R.string.prompt_connection_check))
                builder.setPositiveButton(getString(R.string.prompt_connection_connect)) { _, _ ->
                    val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    this.startActivity(settingsIntent)
                }
                builder.setNegativeButton(getString(R.string.prompt_connection_cancel)) { _, _ -> }
                builder.show()
            }
        }, 2000)
    }
}