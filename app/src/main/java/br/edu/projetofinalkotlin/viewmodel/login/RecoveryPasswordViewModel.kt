package br.edu.projetofinalkotlin.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.edu.projetofinalkotlin.model.repository.login.LoginRepository
import kotlinx.coroutines.Dispatchers

class RecoveryPasswordViewModel(
    private val repository: LoginRepository = LoginRepository.instance
) : ViewModel() {

    fun recoveryPasswordViewModel(email: String) = liveData(Dispatchers.IO) {
        val result = repository.recoveryPassword(email)
        emit(result)
    }
}