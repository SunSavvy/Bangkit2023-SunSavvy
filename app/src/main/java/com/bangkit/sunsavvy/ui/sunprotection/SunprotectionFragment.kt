package com.bangkit.sunsavvy.ui.sunprotection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.databinding.FragmentSunprotectionBinding

class SunprotectionFragment : Fragment() {
    private var _binding: FragmentSunprotectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sunprotectionViewModel = ViewModelProvider(this)[SunprotectionViewModel::class.java]

        _binding = FragmentSunprotectionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.text
        sunprotectionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}