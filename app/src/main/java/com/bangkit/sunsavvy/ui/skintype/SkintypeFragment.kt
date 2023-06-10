package com.bangkit.sunsavvy.ui.skintype

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.databinding.FragmentSkintypeBinding

class SkintypeFragment : Fragment() {
    private var _binding: FragmentSkintypeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val skintypeViewModel = ViewModelProvider(this)[SkintypeViewModel::class.java]

        _binding = FragmentSkintypeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.text
        skintypeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}