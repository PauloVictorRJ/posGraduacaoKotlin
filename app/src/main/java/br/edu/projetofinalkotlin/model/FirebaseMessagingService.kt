package br.edu.projetofinalkotlin.model

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import br.edu.projetofinalkotlin.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body

        val toastMessage =
            "${getString(R.string.fcm_toast_notification_title)} $title\n${getString(R.string.fcm_toast_notification_text)} $body"
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(
                applicationContext,
                toastMessage,
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        getString(R.string.fcm_toast_notification_text)

    }
}