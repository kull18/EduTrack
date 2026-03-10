package com.kull18.edutrack.core.hardware.domain

import android.net.Uri

interface GaleriaManager {
    fun pickImage(onSuccess: (Uri) -> Unit, onError: (Exception) -> Unit)
}