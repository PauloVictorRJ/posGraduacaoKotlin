package br.edu.projetofinalkotlin.viewmodel.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import br.edu.projetofinalkotlin.model.TaskEntity
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import br.edu.projetofinalkotlin.model.repository.task.TaskRepository
import br.edu.projetofinalkotlin.model.room.TaskDatabase
import br.edu.projetofinalkotlin.util.DataResult
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository

    init {
        val dao = TaskDatabase.getDatabase(application).getTaskDao()
        val firebaseDB = Firebase.database.reference
        repository = TaskRepository(dao, firebaseDB)
    }

    suspend fun allTaskViewModel(): LiveData<DataResult<List<TaskEntity>>> =
        repository.getAllTaskRepository().asLiveData()

    fun deleteTaskViewModel(taskEntity: TaskEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTaskRepository(taskEntity)
    }

    fun updateTaskViewModel(taskEntity: TaskEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateTaskRepository(taskEntity)
    }
}