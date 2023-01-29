package com.akinguldere.yktcase.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.akinguldere.yktcase.R
import com.akinguldere.yktcase.YKTActivity
import com.akinguldere.yktcase.databinding.ActivityMainBinding
import com.akinguldere.yktcase.ui.details.DetailsActivity
import com.akinguldere.yktcase.utils.extensions.gone
import com.akinguldere.yktcase.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : YKTActivity() {

    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)

    private val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var productsAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productsAdapter.setOnItemClickListener {
            // Go to product details
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("product", it)
            startActivity(intent)
        }

        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = this@MainActivity.productsAdapter
        }

        viewModel.getProducts()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.responseMovies.observe(this) { response ->
            if (response != null) {
                productsAdapter.differ.submitList(response)
            }
        }

        viewModel.isLoading.observe(this) { response ->
            // Show progressbar when waiting for api result
            if (response) {
                binding.progressBar.visible()
            } else {
                binding.progressBar.gone()
            }
        }
    }
}