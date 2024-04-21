package br.edu.projetofinalkotlin.model.repository.login

import br.edu.projetofinalkotlin.util.DataResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class LoginRepository {

    private var firebaseAuth = FirebaseAuth.getInstance()

    suspend fun createNewUser(email: String, password: String): DataResult<Unit> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            DataResult.Success(Unit)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

    suspend fun loginUser(email: String, password: String): DataResult<Unit> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            DataResult.Success(Unit)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

    suspend fun recoveryPassword(email: String): DataResult<Unit> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            DataResult.Success(Unit)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

    companion object {
        val instance: LoginRepository by lazy { LoginRepository() }
    }
}