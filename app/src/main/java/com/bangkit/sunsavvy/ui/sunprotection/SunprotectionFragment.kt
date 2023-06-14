package com.bangkit.sunsavvy.ui.sunprotection

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.databinding.FragmentSunprotectionBinding
import com.bangkit.sunsavvy.utils.GetColor
import com.bangkit.sunsavvy.utils.OnPressed

class SunprotectionFragment : Fragment() {
    private var _binding: FragmentSunprotectionBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemViewModel: SunprotectionViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: SunprotectionAdapterCategory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSunprotectionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        itemViewModel = ViewModelProvider(this)[SunprotectionViewModel::class.java]
        itemViewModel.category.observe(viewLifecycleOwner) { category ->
            categoryAdapter.setItems(category)
        }

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter = SunprotectionAdapterCategory(emptyList())
        recyclerView = binding.rvSunProtection
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
        }

        binding.cancerCouncilVictoria.setOnClickListener {
            val url = "https://www.cancer.org.au/cancer-information/causes-and-prevention/sun-safety/campaigns-and-events/slip-slop-slap-seek-slide"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        binding.cancerCouncilSSunsmartProgram.setOnClickListener {
            val url = "https://www.cancer.org.au/about-us/policy-and-advocacy/position-statements/sunsmart"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        binding.sidTheSeagull.setOnClickListener {
            val url = "https://www.youtube.com/watch?v=b7nocIenCYg&ab_channel=CancerCouncilVictoria"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        val textView = listOf(binding.cancerCouncilVictoria, binding.cancerCouncilSSunsmartProgram, binding.sidTheSeagull)
        for (text in textView) {
            OnPressed().setTextPressedSecondary(text,
                GetColor.getAccentColor(requireContext()),
                GetColor.getPrimaryColor(requireContext())
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}