package com.bangkit.sunsavvy

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bangkit.sunsavvy.databinding.ActivityMainBinding
import com.bangkit.sunsavvy.ui.settings.SettingsActivity
import com.bangkit.sunsavvy.utils.StringConverter

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    private var resultName: String? = null
    private var resultEmail : String? = null
    private var resultSkin : String? = null
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        drawerLayout = binding.drawerLayout

        val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home,
                R.id.nav_profile,
                R.id.nav_uv,
                R.id.nav_spf_pa,
                R.id.nav_sun_protection,
                R.id.nav_skin_type,
                R.id.nav_sunscreen,
                R.id.nav_about_us
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        val headerView = binding.navView.getHeaderView(0)
        val username = headerView.findViewById<TextView>(R.id.username)
        val email = headerView.findViewById<TextView>(R.id.email)
        val skinType = headerView.findViewById<TextView>(R.id.skin_type)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        resultName = sharedPreferences.getString("PREF_NAME", "")
        resultEmail = sharedPreferences.getString("PREF_EMAIL_RESULT", "")
        resultSkin = sharedPreferences.getString("PREF_SKIN", "")

        Log.d("main email", "$resultEmail")
        username.text = resultName
        email.text = resultEmail

        val romanNumeral = resultSkin?.let { StringConverter.arabicToRoman(it.toInt()) }
        skinType.text = romanNumeral.toString()

        swipeRefreshLayout.setOnRefreshListener {
            Handler().postDelayed({
                recreate()
                swipeRefreshLayout.isRefreshing = false
            }, 500)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.app_bar_main) {
            return true
        }

        when (item.itemId) {
            R.id.settings -> startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}