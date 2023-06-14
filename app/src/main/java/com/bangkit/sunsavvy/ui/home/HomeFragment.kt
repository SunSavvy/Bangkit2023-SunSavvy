package com.bangkit.sunsavvy.ui.home

import android.Manifest
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
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
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.databinding.FragmentHomeBinding
import com.bangkit.sunsavvy.utils.Animator
import com.bangkit.sunsavvy.utils.GetColor
import com.bangkit.sunsavvy.utils.OnPressed
import com.bangkit.sunsavvy.utils.StringConverter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
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

    private val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
    private val meridiemFormat = SimpleDateFormat("a", Locale.getDefault())
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var timeRunnable: Runnable
    private lateinit var cloudAnimators: List<Animator>
    private val speedMultipliers = listOf(0.1f, 0.3f, 0.5f)
    var fusedLocationProviderClient: FusedLocationProviderClient? = null

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.username.observe(viewLifecycleOwner) { username ->
            val frontName = username.split(" ")[0]
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "Hi, $frontName"
        }
        viewModel.uvIndex.observe(viewLifecycleOwner) { uvIndex ->
            binding.uvIndexLevel.text = uvIndex.toString()
            binding.uvIndexLevelAlt.text = uvIndex.toString()
        }
        viewModel.uvCategory.observe(viewLifecycleOwner) { uvCategory ->
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
        }
        viewModel.sunburnTime.observe(viewLifecycleOwner) { sunburnTime ->
            binding.sunburnTime.text = sunburnTime.toString()
        }
        viewModel.skinType.observe(viewLifecycleOwner) { skinType ->
            val romanNumeral = StringConverter.arabicToRoman(skinType)
            binding.skinType.text = romanNumeral
        }
        viewModel.spf.observe(viewLifecycleOwner) { spf ->
            binding.spf.text = "SPF $spf"
        }
        binding.trivia.text = viewModel.trivia.random()

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


    private fun getTimeNow() {
        val currentTime = timeFormat.format(Date())
        val meridiem = meridiemFormat.format(Date())

        binding.clock.text = currentTime
        binding.meridiem.text = meridiem
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