package br.edu.projetofinalkotlin.view.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.edu.projetofinalkotlin.MainActivity
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.databinding.ActivityCreateAccountBinding
import br.edu.projetofinalkotlin.util.DataResult
import br.edu.projetofinalkotlin.view.takepicture.TakePictureActivity
import br.edu.projetofinalkotlin.viewmodel.login.CreateAccountViewModel

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    private val viewModel: CreateAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureButtons()
        intent.getParcelableExtra("userPicture", Uri::class.java)
            ?.let { configureImageViews(it) }
    }

    private fun configureImageViews(userPicture: Uri) {
        binding.picture.setImageURI(userPicture)
    }

    private fun configureButtons() {
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.createAccountBtn.setOnClickListener {
            createNewUser()
        }

        binding.takePictureBtn.setOnClickListener {
            startActivity(Intent(this, TakePictureActivity::class.java))
        }
    }

    private fun createNewUser() {
        val email = binding.createAccountEmailTiet.text.toString()
        val password = binding.createAccountPasswordTiet.text.toString()
        val passwordConfirmation = binding.createAccountPasswordConfirmationTiet.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && passwordConfirmation.isNotEmpty()) {
            if (password == passwordConfirmation) {
                viewModel.createNewUserViewModel(email, password, binding.emailTxt.context)
                    .observe(this) { result ->
                        when (result) {
                            is DataResult.Loading -> {
                                showLoading()
                            }

                            is DataResult.Success -> {
                                hideLoading()
                                showMessage(result.data)
                                startActivity(Intent(this, LoginActivity::class.java))
                            }

                            is DataResult.Error -> {
                                hideLoading()
                                val errorMessage = result.exception.message ?: result.message ?: ""
                                showMessage(errorMessage)
                            }
                        }
                    }
            } else {
                showMessage(
                    getString(R.string.ca_create_account_passwords_do_not_match_txt)
                )
            }
        } else {
            showMessage(
                getString(R.string.ca_create_account_empty_fields_txt)
            )
        }
    }

    private fun showLoading() {
    }

    private fun hideLoading() {
    }

    private fun showMessage(message: String) {
        Toast.makeText(this@CreateAccountActivity, message, Toast.LENGTH_SHORT).show()
    }
}