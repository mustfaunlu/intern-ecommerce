package com.mustafaunlu.ecommerce

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mustafaunlu.ecommerce.presentation.MainActivity


class NotificationWorker( private val appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        showNotification()
        Log.d(TAG, "Performing long running task in scheduled job")
        return Result.success()
    }

    @SuppressLint("MissingPermission")
    private fun showNotification() {
        val intent = PendingIntent.getActivity(appContext, 0, Intent(appContext, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE)
        val notificationManager = NotificationManagerCompat.from(appContext)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "MyNotificationChannel"
            val description = "My Notification Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("channelId", name, importance)
            channel.description = description
            notificationManager.createNotificationChannel(channel)

        }
        val builder = NotificationCompat.Builder(appContext, "channelId")
            .setContentTitle("Title")
            .setContentText("Content")
            .setContentIntent(intent)
            .setSmallIcon(R.drawable.ic_favorite_filled)
            .build()
        notificationManager.notify(1, builder)

    }
    companion object {
        private val TAG = "NotificationWorker"
    }
}

