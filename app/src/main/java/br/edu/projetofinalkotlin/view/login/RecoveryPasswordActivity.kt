package br.edu.projetofinalkotlin.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.databinding.ActivityRecoveryPasswordBinding
import br.edu.projetofinalkotlin.util.DataResult
import br.edu.projetofinalkotlin.viewmodel.login.RecoveryPasswordViewModel

class RecoveryPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecoveryPasswordBinding
    private val viewModel: RecoveryPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoveryPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureButtons()
    }

    private fun configureButtons() {
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.recoveryBtn.setOnClickListener {
            recoveryPassword()
        }
    }

    private fun recoveryPassword() {
        val email = binding.loginEmailTiet.text.toString()

        if (email.isNotEmpty()) {
            viewModel.recoveryPasswordViewModel(email).observe(this) { result ->
                when (result) {
                    is DataResult.Loading -> {
                        showLoading()
                    }

                    is DataResult.Success -> {
                        hideLoading()
                        showMessage(getString(R.string.rp_receive_code_sent_toast_txt))
                        startActivity(Intent(this, LoginActivity::class.java))
                    }

                    is DataResult.Error -> {
                        hideLoading()
                        showMessage(getString(R.string.rp_receive_code_sent_toast_error_txt) + " ${result.exception.message}")
                    }
                }
            }
        } else {
            showMessage(getString(R.string.login_toast_empty_fields_txt))
        }
    }

    private fun showLoading() {
    }

    private fun hideLoading() {
    }

    private fun showMessage(message: String) {
        Toast.makeText(this@RecoveryPasswordActivity, message, Toast.LENGTH_SHORT).show()
    }
}