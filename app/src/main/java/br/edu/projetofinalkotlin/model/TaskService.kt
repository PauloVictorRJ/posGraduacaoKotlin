package br.edu.projetofinalkotlin.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.model.repository.task.TaskRepository
import br.edu.projetofinalkotlin.model.room.TaskDatabase
import br.edu.projetofinalkotlin.util.DataResult
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskService : Service() {
    private var allTasksExpired: List<TaskEntity> = listOf()
    private lateinit var repository: TaskRepository
    private lateinit var appContext: Context

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        val dao = TaskDatabase.getDatabase(appContext).getTaskDao()
        val firebaseDB = Firebase.database.reference
        repository = TaskRepository(dao, firebaseDB)
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getAllTasksExpiredService().collect { result ->
                when (result) {
                    is DataResult.Success -> {
                        val data = result.data
                        allTasksExpired = data
                        if (data.isNotEmpty()) {
                            sendMsg(data)
                        }
                    }

                    is DataResult.Error -> {}
                    DataResult.Loading -> {}
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun sendMsg(tasks: List<TaskEntity>) {
        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            "Task_channel", "Channel_Name",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        val notificationBuilder =
            NotificationCompat.Builder(applicationContext, "Task_channel")
                .setContentTitle(
                    ContextCompat.getString(
                        appContext,
                        R.string.tasks_service_notification_message_title
                    )
                )
                .setSmallIcon(androidx.core.R.drawable.notification_bg)
                .setAutoCancel(true)

        val notificationText = StringBuilder()
        for (item in tasks) {
            val title = item.title
            notificationText.append("$title\n")
        }
        notificationBuilder.setContentText(notificationText.toString())
        notificationManager.notify(12, notificationBuilder.build())
    }
}