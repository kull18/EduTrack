package com.kull18.edutrack.core.hardware.domain

interface NotificacionManager {
    fun mostrarNotificacion(titulo: String, mensaje: String)
    fun crearCanalNotificaciones()
}