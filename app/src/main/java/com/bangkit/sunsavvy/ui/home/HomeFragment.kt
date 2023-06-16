package com.bangkit.sunsavvy.ui.home

import android.Manifest
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.databinding.FragmentHomeBinding
import com.bangkit.sunsavvy.utils.Animator
import com.bangkit.sunsavvy.utils.CustomDialog
import com.bangkit.sunsavvy.utils.GetColor
import com.bangkit.sunsavvy.utils.StringConverter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    companion object {
        private val REQUEST_CODE = 100
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var forecastViewModel: ForecastViewModel
    private lateinit var classifyViewModel: ClassifyViewModel

    private var username: String? = null
    private var skinType: String? = null
    private lateinit var sharedPreferences: SharedPreferences

    private val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
    private val meridiemFormat = SimpleDateFormat("a", Locale.getDefault())
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var timeRunnable: Runnable
    private lateinit var cloudAnimators: List<Animator>
    private val speedMultipliers = listOf(0.1f, 0.3f, 0.5f)
    var fusedLocationProviderClient: FusedLocationProviderClient? = null

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        username = sharedPreferences.getString("PREF_NAME", "")
        val frontName = username!!.split(" ")[0]
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Hi, $frontName!"
        activity?.title = "Hi, $frontName!"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        showLoading(true)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        skinType = sharedPreferences.getString("PREF_SKIN", "")

        val romanNumeral = skinType?.let { StringConverter.arabicToRoman(it.toInt()) }
        binding.skinType.text = romanNumeral.toString()

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.trivia.text = viewModel.trivia.random()
        binding.cardTrivia.setOnClickListener {
            binding.trivia.text = viewModel.trivia.random()
        }
        binding.trivia.text = viewModel.trivia.random()

        classifyViewModel = ViewModelProvider(this)[ClassifyViewModel::class.java]

        classifyViewModel.result.observe(requireActivity()){result ->
            if (result != null){
                val num = result.data?.get(0)?.spf.toString()
                binding.spf.text = "SPF $num"
            }
        }
        classifyViewModel.getSUV()

        getUV()

        classifyViewModel = ViewModelProvider(this)[ClassifyViewModel::class.java]

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        getLastLocation()
        getTimeNow()
        animateCloud()
        animateFloating()
    }

    override fun onResume() {
        super.onResume()
        startUpdatingTime()
    }

    override fun onPause() {
        super.onPause()
        stopUpdatingTime()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        cloudAnimators.forEach { it.stopAnimation() }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            } else {
                Toast.makeText(requireContext(), "Required Permission", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun animateCloud() {
        val clouds = listOf(binding.cloud1, binding.cloud2, binding.cloud3)
        cloudAnimators = clouds.zip(speedMultipliers).map { (cloud, speedMultiplier) -> Animator(cloud, resources.displayMetrics.widthPixels, speedMultiplier) }

        cloudAnimators.forEach { it.startAnimation() }
    }

    private fun animateFloating() {
        val animDuration = 2000L
        val animDistance = 20f

        val animator = ObjectAnimator.ofFloat(binding.sunscreen, View.TRANSLATION_Y, 0f, animDistance)
        animator.duration = animDuration
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBar2.visibility = View.VISIBLE
            binding.uvIndexLevel.visibility = View.INVISIBLE
            binding.uvIndexLevelAlt.visibility = View.INVISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
            binding.progressBar2.visibility = View.INVISIBLE
            binding.uvIndexLevel.visibility = View.VISIBLE
            binding.uvIndexLevelAlt.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUV() {

        val hour = SimpleDateFormat("HH", Locale.getDefault()).format(Date())

        forecastViewModel = ViewModelProvider(this)[ForecastViewModel::class.java]

        val id = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("PREF_TOKEN", "")
        forecastViewModel.getUV(id)

        forecastViewModel.result.observe(requireActivity()) { result ->
            if (result != null) {
                val uvIndex = result.data?.predictions?.get(0)?.get(hour.toInt())
                saveUvIndex(uvIndex.toString())

                if (uvIndex != null) {
                    val uvIndexTrimmed = BigDecimal(uvIndex).setScale(1, BigDecimal.ROUND_HALF_UP)

                    binding.uvIndexLevel.text = uvIndexTrimmed.toString()
                    binding.uvIndexLevelAlt.text = uvIndexTrimmed.toString()
                    val uvCategory = when (uvIndexTrimmed.toDouble()) {
                        in 0.0..0.4 -> "Low"
                        in 0.5..3.4 -> "Medium"
                        in 3.5..6.4 -> "High"
                        in 6.5..9.4 -> "Very High"
                        else -> "Extreme"
                    }
                    binding.uvCategory.text = "$uvCategory UV Index"
                    when (uvCategory) {
                        "Extreme" -> {
                            binding.uvIndexLevelAlt.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.uv_extreme))
                            val slogans = listOf(binding.sloganSlip, binding.sloganSlop, binding.sloganSlap, binding.sloganSeek, binding.sloganSlide)
                            for (slogan in slogans) {
                                onSlogan(slogan)
                            }
                        }
                        "Very High" -> {
                            binding.uvIndexLevelAlt.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.uv_very_high))
                            val slogans = listOf(binding.sloganSlip, binding.sloganSlop, binding.sloganSlap, binding.sloganSlide)
                            for (slogan in slogans) {
                                onSlogan(slogan)
                            }
                        }
                        "High" -> {
                            binding.uvIndexLevelAlt.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.uv_high))
                            val slogans = listOf(binding.sloganSlop, binding.sloganSlap, binding.sloganSlide)
                            for (slogan in slogans) {
                                onSlogan(slogan)
                            }
                        }
                        "Medium" -> {
                            binding.uvIndexLevelAlt.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.uv_medium))
                            val slogans = listOf(binding.sloganSlop, binding.sloganSlide)
                            for (slogan in slogans) {
                                onSlogan(slogan)
                            }
                        }
                        "Low" -> binding.uvIndexLevelAlt.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.uv_low))
                    }

                    when (skinType!!.toInt()) {
                        in 0..1 -> {
                            binding.sunburnTime.text = when (uvIndexTrimmed.toDouble()) {
                                in 0.0..0.9 -> "No risk of sunburn"
                                in 1.0..3.9 -> "Over 60 minutes"
                                in 4.0..6.9 -> "Around 30 minutes"
                                in 7.0..9.9 -> "Around 20 minutes"
                                else -> "Less than 15 minutes"
                            }
                        }
                        else -> {
                            binding.sunburnTime.text = when (uvIndexTrimmed.toDouble()) {
                                in 0.0..0.9 -> "No risk of sunburn"
                                in 1.0..3.9 -> "Over 60 minutes"
                                in 4.0..6.9 -> "Around 60 minutes"
                                in 7.0..9.9 -> "Around 40 minutes"
                                else -> "Less than 30 minutes"
                            }
                        }
                    }
                }
                showLoading(false)
            }
        }
    }

    private fun getTimeNow() {
        val currentTime = timeFormat.format(Date())
        val meridiem = meridiemFormat.format(Date())

        binding.clock.text = currentTime
        binding.meridiem.text = meridiem
    }

    private fun saveUvIndex(token: String?) {
        PreferenceManager.getDefaultSharedPreferences(requireActivity())
            .edit()
            .putString("PREF_INDEX", token)
            .apply()
    }

    private fun startUpdatingTime() {
        timeRunnable = object : Runnable {
            override fun run() {
                getTimeNow()

                handler.postDelayed(this, 500)
            }
        }
        handler.post(timeRunnable)
    }

    private fun stopUpdatingTime() {
        handler.removeCallbacks(timeRunnable)
    }

    private fun getLastLocation() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient!!.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val geocoder = Geocoder(requireActivity(), Locale("ID"))
                    val addresses: List<Address>?
                    try {
                        addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        binding.location.text = addresses!![0].adminArea

                        forecastViewModel.setAdminArea(addresses[0].adminArea)
                        forecastViewModel.setLatitude(location.latitude)
                        forecastViewModel.setLongitude(location.longitude)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

        } else {
            askPermission()
        }
    }

    private fun askPermission() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun onSlogan(slogan: TextView) {
        slogan.setTextColor(ColorStateList.valueOf(GetColor.getPrimaryColor(requireContext())))
        slogan.backgroundTintList = ColorStateList.valueOf(GetColor.getAccentColor(requireContext()))
        slogan.compoundDrawableTintList = ColorStateList.valueOf(GetColor.getPrimaryColor(requireContext()))
    }
}