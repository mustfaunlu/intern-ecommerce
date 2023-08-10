package com.mustafaunlu.ecommerce

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mustafaunlu.ecommerce.ui.MainActivity


class NotificationWorker( private val appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    @SuppressLint("MissingPermission")
    private fun showNotification() {
        val intent = PendingIntent.getActivity(appContext, INTENT_REQ_CODE, Intent(appContext, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE)
        val notificationManager = NotificationManagerCompat.from(appContext)
        val name: CharSequence = NOTIFICATION_CH_NAME
        val description = NOTIFICATION_CH_DESC
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(NOTIFICATION_CH_ID, name, importance)
        channel.description = description
        notificationManager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(appContext, NOTIFICATION_CH_ID)
            .setContentTitle("Title")
            .setContentText("Content")
            .setContentIntent(intent)
            .setSmallIcon(R.drawable.ic_favorite_filled)
            .build()
        notificationManager.notify(NOTIFICATION_ID, builder)

    }
    companion object {
        private const val NOTIFICATION_ID = 1
        private const val NOTIFICATION_CH_NAME = "Ecommerce"
        private const val NOTIFICATION_CH_DESC = "Ecommerce Notification"
        private const val NOTIFICATION_CH_ID = "EcommerceNotfChannelId"
        private const val INTENT_REQ_CODE = 0
    }
}

