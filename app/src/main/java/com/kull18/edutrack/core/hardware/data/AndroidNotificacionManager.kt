package com.kull18.edutrack.core.hardware.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kull18.edutrack.R
import com.kull18.edutrack.core.hardware.domain.NotificacionManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidNotificacionManager @Inject constructor(
    @ApplicationContext private val context: Context
) : NotificacionManager {

    companion object {
        const val CHANNEL_ID = "edutrack_channel"
        const val CHANNEL_NAME = "EduTrack Notificaciones"
        const val CHANNEL_DESCRIPTION = "Notificaciones de inscripciones y progreso"
        private var notificationId = 0
    }

    override fun crearCanalNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = CHANNEL_DESCRIPTION
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun mostrarNotificacion(titulo: String, mensaje: String) {
        // Crear el canal primero (en Android 8+ es obligatorio)
        crearCanalNotificaciones()

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // usa tu ícono
            .setContentTitle(titulo)
            .setContentText(mensaje)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(mensaje)
            )

        try {
            NotificationManagerCompat.from(context).notify(notificationId++, builder.build())
        } catch (e: SecurityException) {
            // El usuario no otorgó permiso POST_NOTIFICATIONS (Android 13+)
            e.printStackTrace()
        }
    }
}