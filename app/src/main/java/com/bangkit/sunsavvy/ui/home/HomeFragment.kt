package com.bangkit.sunsavvy.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.sunsavvy.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HomeAdapter
    private lateinit var viewModel: HomeViewModel
    private val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
    private val meridiemFormat = SimpleDateFormat("a", Locale.getDefault())
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var timeRunnable: Runnable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = HomeAdapter()
        binding.rvSlogan.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSlogan.adapter = adapter

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.slogan.observe(viewLifecycleOwner) { items ->
            adapter.setItems(items)
        }
        viewModel.title.observe(viewLifecycleOwner) { title ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = title
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        startUpdatingTime()
    }

    override fun onPause() {
        super.onPause()
        stopUpdatingTime()
    }

    private fun startUpdatingTime() {
        timeRunnable = object : Runnable {
            override fun run() {
                val currentTime = timeFormat.format(Date())
                val meridiem = meridiemFormat.format(Date())

                binding.clock.text = currentTime
                binding.meridiem.text = meridiem

                handler.postDelayed(this, 1000)
            }
        }
        handler.post(timeRunnable)
    }

    private fun stopUpdatingTime() {
        handler.removeCallbacks(timeRunnable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}