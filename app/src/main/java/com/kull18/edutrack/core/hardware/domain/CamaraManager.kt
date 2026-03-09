package com.kull18.edutrack.core.hardware.domain

interface CamaraManager {
    fun takePhoto(onSuccess: (ByteArray) -> Unit, onError: (Exception) -> Unit)
    fun release()
}