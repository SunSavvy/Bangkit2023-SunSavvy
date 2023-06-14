package com.bangkit.sunsavvy.ui.skintype

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.databinding.FragmentSkintypeBinding

class SkintypeFragment : Fragment() {
    private var _binding: FragmentSkintypeBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemViewModel: SkintypeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: SkintypeAdapterCategory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSkintypeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        itemViewModel = ViewModelProvider(this)[SkintypeViewModel::class.java]
        itemViewModel.category.observe(viewLifecycleOwner) { category ->
            categoryAdapter.setItems(category)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter = SkintypeAdapterCategory(emptyList())
        recyclerView = binding.rvSkinType
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