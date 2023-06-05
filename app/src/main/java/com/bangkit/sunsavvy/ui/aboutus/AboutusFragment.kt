package com.bangkit.sunsavvy.ui.aboutus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.databinding.FragmentAboutusBinding

class AboutusFragment : Fragment() {
    private var _binding: FragmentAboutusBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val aboutusViewModel = ViewModelProvider(this)[AboutusViewModel::class.java]

        _binding = FragmentAboutusBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.text
//        aboutusViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}