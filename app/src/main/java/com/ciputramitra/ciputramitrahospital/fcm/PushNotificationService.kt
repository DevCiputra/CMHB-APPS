package com.ciputramitra.ciputramitrahospital.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.core.content.edit
import com.ciputramitra.ciputramitrahospital.MainActivity
import com.ciputramitra.ciputramitrahospital.R

class PushNotificationService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        CoroutineScope(Dispatchers.IO).launch {
            // Gunakan API Anda untuk update token
            // apiService.updateFcmToken(token)

            // Atau simpan di SharedPreferences lokal
            getSharedPreferences("fcm_prefs", Context.MODE_PRIVATE)
                .edit {
                    putString("fcm_token", token)
                }
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.let {
            sendNotification(it.title, it.body)
        }

        // Periksa apakah pesan berisi data payload
        if (remoteMessage.data.isNotEmpty()) {

            // Untuk data yang memerlukan proses di background
            if (needsBackgroundProcessing(remoteMessage.data)) {
                scheduleBackgroundTask(remoteMessage.data)
            } else {
                handleNow(remoteMessage.data)
            }
        }
    }

    private fun sendNotification(title: String?, body: String?) {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra("notification", true)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(title ?: getString(R.string.notification_title))
            .setContentText(body ?: getString(R.string.notification_message))
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Android Oreo (API 26+) memerlukan notification channel
        val channel = NotificationChannel(
            channelId,
            getString(R.string.default_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "General notifications from app"
            enableLights(true)
            enableVibration(true)
        }
        notificationManager.createNotificationChannel(channel)

        // Tampilkan notifikasi dengan ID unik
        val notificationId = System.currentTimeMillis().toInt()
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun needsBackgroundProcessing(data: Map<String, String>): Boolean {
        // Tentukan apakah data perlu diproses di background
        return data["background_processing"] == "true"
    }

    private fun scheduleBackgroundTask(data: Map<String, String>) {
        // Implementasi untuk background task menggunakan WorkManager
        // ...
    }

    private fun handleNow(data: Map<String, String>) {
        // Proses data payload segera
        // ...
    }
}