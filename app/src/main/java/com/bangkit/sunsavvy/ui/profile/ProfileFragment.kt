package com.bangkit.sunsavvy.ui.profile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.auth.ui.SplashActivity
import com.bangkit.sunsavvy.auth.ui.RegisterActivity
import com.bangkit.sunsavvy.databinding.FragmentProfileBinding
import com.bangkit.sunsavvy.utils.OnPressed
import com.bangkit.sunsavvy.utils.GetColor.Companion.getErrorColor
import com.bangkit.sunsavvy.utils.GetColor.Companion.getPrimaryColor
import com.bangkit.sunsavvy.utils.StringConverter

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.username.observe(viewLifecycleOwner) { username ->
            binding.username.text = username
        }
        viewModel.email.observe(viewLifecycleOwner) { email ->
            binding.email.text = email
        }
        viewModel.skinType.observe(viewLifecycleOwner) { skinType ->
            val romanNumeral = StringConverter.arabicToRoman(skinType)
            binding.skinType.text = romanNumeral
        }

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnUpdate.setOnClickListener {
            // TODO("Intent to RegisterActivity but with some extra to edit profile. Also extra that change the button text to "Update Profile")
            val intent = Intent(requireContext(), RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogOut.setOnClickListener {
            // TODO("Logout from user data and intent to SplashActivity")
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