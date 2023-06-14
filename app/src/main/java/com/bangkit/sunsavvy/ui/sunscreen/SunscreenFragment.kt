package com.bangkit.sunsavvy.ui.sunscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.databinding.FragmentSunscreenBinding

class SunscreenFragment : Fragment() {
    private var _binding: FragmentSunscreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemViewModel: SunscreenViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var informationAdapter: SunscreenAdapterInformation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSunscreenBinding.inflate(inflater, container, false)
        val root: View = binding.root

        itemViewModel = ViewModelProvider(this)[SunscreenViewModel::class.java]
        itemViewModel.information.observe(viewLifecycleOwner) { information ->
            informationAdapter.setItems(information)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        informationAdapter = SunscreenAdapterInformation(emptyList())
        recyclerView = binding.rvSunscreenInformation
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = informationAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}