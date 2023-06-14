package com.bangkit.sunsavvy.ui.spfpa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.databinding.FragmentSpfpaBinding
import com.bangkit.sunsavvy.ui.uv.UvAdapterCategory
import com.bangkit.sunsavvy.ui.uv.UvAdapterInformation
import com.bangkit.sunsavvy.ui.uv.UvViewModel

class SpfpaFragment : Fragment() {
    private var _binding: FragmentSpfpaBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemViewModel: SpfpaViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var informationAdapter: SpfpaAdapterInformation
    private lateinit var categoryAdapter: SpfpaAdapterCategory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSpfpaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        itemViewModel = ViewModelProvider(this)[SpfpaViewModel::class.java]
        itemViewModel.information.observe(viewLifecycleOwner) { information ->
            informationAdapter.setItems(information)
        }
        itemViewModel.category.observe(viewLifecycleOwner) { category ->
            categoryAdapter.setItems(category)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        informationAdapter = SpfpaAdapterInformation(emptyList())
        recyclerView = binding.rvSpfPaInformation
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = informationAdapter
        }

        categoryAdapter = SpfpaAdapterCategory(emptyList())
        recyclerView = binding.rvSpfPaLevel
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}