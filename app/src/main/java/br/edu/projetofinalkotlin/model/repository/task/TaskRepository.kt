package br.edu.projetofinalkotlin.model.repository.task

import androidx.annotation.WorkerThread
import br.edu.projetofinalkotlin.model.TaskEntity
import br.edu.projetofinalkotlin.model.room.TaskDao
import br.edu.projetofinalkotlin.util.DataResult
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TaskRepository(private val taskDao: TaskDao, private val firebaseDB: DatabaseReference) {

    @WorkerThread
    suspend fun insertTaskRepository(taskEntity: TaskEntity) {
        taskDao.insertTaskDao(taskEntity)
        val insertedId = taskDao.getLastInsertedTaskIdDao()

        if (insertedId != null) {
            taskEntity.id = insertedId
            firebaseDB.child("task").child(insertedId.toString()).setValue(taskEntity)
        }
    }

    @WorkerThread
    suspend fun deleteTaskRepository(taskEntity: TaskEntity) {
        taskDao.deleteTaskDao(taskEntity.id)
        firebaseDB.child("task").child(taskEntity.id.toString()).removeValue()
    }

    @WorkerThread
    suspend fun updateTaskRepository(taskEntity: TaskEntity) {
        taskEntity.id?.let {
            taskDao.updateTaskDao(
                it,
                taskEntity.title,
                taskEntity.description,
                taskEntity.condition
            )
        }
        firebaseDB.child("task").child(taskEntity.id.toString()).apply {
            child("title").setValue(taskEntity.title)
            child("description").setValue(taskEntity.description)
            child("condition").setValue(taskEntity.condition)
        }
    }

    suspend fun getAllTaskRepository(): Flow<DataResult<List<TaskEntity>>> =
        flow {
            try {
                taskDao.getAllTasksDao().collect { tasks ->
                    emit(DataResult.Success(tasks))
                }
            } catch (e: Exception) {
                emit(DataResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getAllTasksExpiredService(): Flow<DataResult<List<TaskEntity>>> =
        flow {
            try {
                taskDao.getAllTasksExpiredServiceDao().collect { tasks ->
                    emit(DataResult.Success(tasks))
                }
            } catch (e: Exception) {
                emit(DataResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)

}
