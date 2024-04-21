package br.edu.projetofinalkotlin.viewmodel.login

import android.content.Context
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.model.repository.login.LoginRepository
import br.edu.projetofinalkotlin.util.DataResult
import kotlinx.coroutines.Dispatchers

class CreateAccountViewModel(
    private val repository: LoginRepository = LoginRepository.instance
) : ViewModel() {

    fun createNewUserViewModel(email: String, password: String, context: Context) =
        liveData(Dispatchers.IO) {
            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex())) {
                emit(
                    DataResult.Error(
                        Exception(),
                        message = getString(
                            context,
                            R.string.ca_create_account_invalid_email_error_txt
                        )
                    )
                )
            } else if (!"(?=.*[A-Z])".toRegex().containsMatchIn(password)) {
                emit(
                    DataResult.Error(
                        Exception(),
                        message = getString(
                            context,
                            R.string.ca_create_account_passwords_uppercase_error_txt
                        )
                    )
                )
            } else if (password.length < 6) {
                emit(
                    DataResult.Error(
                        Exception(),
                        message = getString(
                            context,
                            R.string.ca_create_account_passwords_6digits_error_txt
                        )
                    )
                )
            } else {
                emit(
                    DataResult.Success(
                        getString(
                            context,
                            R.string.ca_create_account_toast_success
                        )
                    )
                )
                repository.createNewUser(email, password)
            }
        }
}
