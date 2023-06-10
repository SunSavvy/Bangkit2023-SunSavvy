package com.bangkit.sunsavvy.ui.aboutus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.databinding.FragmentAboutusBinding

class AboutusFragment : Fragment() {
    private var _binding: FragmentAboutusBinding? = null
    private val binding get() = _binding!!
    private lateinit var teamViewModel: AboutusViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var teamAdapter: AboutusAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAboutusBinding.inflate(inflater, container, false)
        val root: View = binding.root

        teamViewModel = ViewModelProvider(this)[AboutusViewModel::class.java]
        teamViewModel.items.observe(viewLifecycleOwner) { items ->
            teamAdapter.setItems(items)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamAdapter = AboutusAdapter(emptyList())
        recyclerView = binding.rvSocial
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = teamAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}