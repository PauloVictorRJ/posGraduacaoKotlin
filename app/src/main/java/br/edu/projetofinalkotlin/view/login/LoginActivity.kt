package br.edu.projetofinalkotlin.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.edu.projetofinalkotlin.MainActivity
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.databinding.ActivityLoginBinding
import br.edu.projetofinalkotlin.util.DataResult
import br.edu.projetofinalkotlin.view.home.HomeActivity
import br.edu.projetofinalkotlin.viewmodel.login.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureButtons()
    }

    private fun configureButtons() {
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
            loginNewUser()
        }

        binding.notYetNewAccountBtn.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }

        binding.recoveryBtn.setOnClickListener {
            startActivity(Intent(this, RecoveryPasswordActivity::class.java))
        }
    }

    private fun loginNewUser() {
        val email = binding.loginEmailTiet.text.toString()
        val password = binding.loginPasswordTiet.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.loginUserViewModel(email, password).observe(this) { result ->
                when (result) {
                    is DataResult.Loading -> {
                        showLoading()
                    }

                    is DataResult.Success -> {
                        hideLoading()
                        showMessage(getString(R.string.login_account_toast_success))
                        startActivity(Intent(this, HomeActivity::class.java))
                    }

                    is DataResult.Error -> {
                        hideLoading()
                        showMessage(getString(R.string.login_account_toast_error) + " ${result.exception.message}")
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
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
    }
}