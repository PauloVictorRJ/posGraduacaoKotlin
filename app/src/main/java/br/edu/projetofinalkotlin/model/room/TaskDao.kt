package br.edu.projetofinalkotlin.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.edu.projetofinalkotlin.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskDao(taskEntity: TaskEntity)

    @Query("SELECT * FROM task ORDER BY id ASC")
    fun getAllTasksDao(): Flow<List<TaskEntity>>

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTaskDao(id: Int?)

    @Query("SELECT id FROM task ORDER BY id DESC LIMIT 1")
    suspend fun getLastInsertedTaskIdDao(): Int?

    @Query("SELECT * FROM task WHERE condition=TRUE ORDER BY id ASC")
    fun getAllTasksExpiredServiceDao(): Flow<List<TaskEntity>>

    @Query("UPDATE task SET title = :title, description = :description, condition = :condition WHERE id = :id")
    suspend fun updateTaskDao(id: Int, title: String, description: String, condition: Boolean)
}