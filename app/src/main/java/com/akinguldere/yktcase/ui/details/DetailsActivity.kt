package com.akinguldere.yktcase.ui.details

import android.os.Bundle
import android.widget.Toast
import com.akinguldere.yktcase.R
import com.akinguldere.yktcase.YKTActivity
import com.akinguldere.yktcase.databinding.ActivityDetailsBinding
import com.akinguldere.yktcase.model.FSProduct

class DetailsActivity : YKTActivity() {

    private val binding by binding<ActivityDetailsBinding>(R.layout.activity_details)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fsProduct = intent.getSerializableExtra("product") as FSProduct

        binding.apply {
            lifecycleOwner = this@DetailsActivity
            product = fsProduct

            button.setOnClickListener {
                Toast.makeText(
                    this@DetailsActivity,
                    getString(R.string.added_to_cart_successfully),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
}