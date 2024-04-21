package br.edu.projetofinalkotlin.viewmodel.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.edu.projetofinalkotlin.model.TaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import br.edu.projetofinalkotlin.model.repository.task.TaskRepository
import br.edu.projetofinalkotlin.model.room.TaskDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EditTaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository

    init {
        val dao = TaskDatabase.getDatabase(application).getTaskDao()
        val firebaseDB = Firebase.database.reference
        repository = TaskRepository(dao, firebaseDB)
    }

    fun insertTaskViewModel(taskEntity: TaskEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTaskRepository(taskEntity)
    }
}