package com.bangkit.sunsavvy.ui.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.ui.auth.SplashActivity
import com.bangkit.sunsavvy.ui.auth.register.RegisterActivity
import com.bangkit.sunsavvy.databinding.FragmentProfileBinding
import com.bangkit.sunsavvy.utils.OnPressed
import com.bangkit.sunsavvy.utils.GetColor.Companion.getErrorColor
import com.bangkit.sunsavvy.utils.GetColor.Companion.getPrimaryColor
import com.bangkit.sunsavvy.utils.StringConverter

@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var viewModel: ProfileViewModel
    private var resultName: String? = null
    private var resultEmail : String? = null
    private var resultSkin : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        resultName = sharedPreferences.getString("PREF_NAME_RESULT", "")
        resultEmail = sharedPreferences.getString("PREF_EMAIL_RESULT", "")
        resultSkin = sharedPreferences.getString("PREF_SKIN_RESULT", "")

        Log.d("hasil profile", "$resultName, $resultEmail, $resultSkin")

        binding.username.text = resultName
        binding.email.text = resultEmail
        binding.email.text = resultSkin

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(requireContext(), RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogOut.setOnClickListener {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
            sharedPreferences.edit().remove("PREF_TOKEN").apply()
            sharedPreferences.edit().remove("PREF_NAME").apply()
            sharedPreferences.edit().remove("PREF_SKIN").apply()
            sharedPreferences.edit().remove("PREF_EMAIL").apply()
            val intent = Intent(requireContext(), SplashActivity::class.java)
            startActivity(intent)
        }

        OnPressed().setButtonPressedPrimary(binding.btnUpdate)
        OnPressed().setButtonPressedSecondary(binding.btnLogOut, getPrimaryColor(requireContext()), getErrorColor(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}