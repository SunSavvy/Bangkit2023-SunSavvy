package com.bangkit.sunsavvy.ui.campaign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sunsavvy.databinding.FragmentCampaignBinding

class CampaignFragment : Fragment() {
    private var _binding: FragmentCampaignBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val campaignViewModel = ViewModelProvider(this)[CampaignViewModel::class.java]

        _binding = FragmentCampaignBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.text
        campaignViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}