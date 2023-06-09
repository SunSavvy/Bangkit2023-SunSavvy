package com.bangkit.sunsavvy.ui.uv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.databinding.FragmentUvBinding

class UvFragment : Fragment() {
    private var _binding: FragmentUvBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemViewModel: UvViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: UvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUvBinding.inflate(inflater, container, false)
        val root: View = binding.root

        itemViewModel = ViewModelProvider(this)[UvViewModel::class.java]
        itemViewModel.items.observe(viewLifecycleOwner) { items ->
            itemAdapter.setItems(items)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemAdapter = UvAdapter(emptyList())
        recyclerView = binding.rvUv
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView.adapter = null
    }
}
