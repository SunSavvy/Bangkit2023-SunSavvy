package com.bangkit.sunsavvy.ui.skintype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.databinding.ActivitySkintypeBinding

@Suppress("DEPRECATION")
class SkintypeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySkintypeBinding
    private lateinit var itemViewModel: SkintypeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: SkintypeAdapterCategory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkintypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSetting)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.menu_skin_type)
        }

        itemViewModel = ViewModelProvider(this)[SkintypeViewModel::class.java]
        itemViewModel.category.observe(this) { category ->
            categoryAdapter.setItems(category)
        }

        categoryAdapter = SkintypeAdapterCategory(emptyList())
        recyclerView = binding.rvSkinType
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SkintypeActivity)
            adapter = categoryAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
