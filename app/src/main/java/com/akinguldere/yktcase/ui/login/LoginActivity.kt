package com.akinguldere.yktcase.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.akinguldere.yktcase.R
import com.akinguldere.yktcase.YKTActivity
import com.akinguldere.yktcase.databinding.ActivityLoginBinding
import com.akinguldere.yktcase.model.LoginRequest
import com.akinguldere.yktcase.ui.main.MainActivity
import com.akinguldere.yktcase.utils.extensions.gone
import com.akinguldere.yktcase.utils.extensions.visible

class LoginActivity : YKTActivity() {

    private val binding by binding<ActivityLoginBinding>(R.layout.activity_login)

    private val viewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            button.setOnClickListener {
                val request =
                    LoginRequest(usernameEditText.text.toString(), passwordEditText.text.toString())
                viewModel.login(request)
            }
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.loginResponse.observe(this) { response ->
            if (response != null) {
                goToMain()
            }
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
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

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}