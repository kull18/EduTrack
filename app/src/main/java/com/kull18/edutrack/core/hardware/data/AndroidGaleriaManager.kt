package com.kull18.edutrack.core.hardware.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import com.kull18.edutrack.core.hardware.domain.GaleriaManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidGaleriaManager @Inject constructor(
    @ApplicationContext private val context: Context
) : GaleriaManager {

    // Launcher que se registra desde la Activity/Composable
    // Se asigna externamente antes de llamar pickImage()
    var launcher: ActivityResultLauncher<String>? = null

    private var onSuccessCallback: ((Uri) -> Unit)? = null
    private var onErrorCallback: ((Exception) -> Unit)? = null

    override fun pickImage(onSuccess: (Uri) -> Unit, onError: (Exception) -> Unit) {
        onSuccessCallback = onSuccess
        onErrorCallback = onError

        val activeLauncher = launcher
        if (activeLauncher == null) {
            onError(Exception("Launcher no registrado. Llama a registerLauncher() desde el Composable."))
            return
        }

        activeLauncher.launch("image/*")
    }

    /**
     * Llamar este método desde el Composable cuando se obtenga la Uri seleccionada.
     * Ejemplo:
     *   val launcher = rememberLauncherForActivityResult(GetContent()) { uri ->
     *       galeriaManager.onImagePicked(uri)
     *   }
     *   galeriaManager.launcher = launcher
     */
    fun onImagePicked(uri: Uri?) {
        if (uri != null) {
            onSuccessCallback?.invoke(uri)
        } else {
            onErrorCallback?.invoke(Exception("No se seleccionó ninguna imagen"))
        }
        onSuccessCallback = null
        onErrorCallback = null
    }
}