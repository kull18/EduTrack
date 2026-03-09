package com.kull18.edutrack.core.hardware.data

import android.content.Context
import android.graphics.ImageFormat
import android.hardware.camera2.*
import android.media.ImageReader
import android.os.Handler
import android.os.HandlerThread
import com.kull18.edutrack.core.hardware.domain.CamaraManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidCamaraManager @Inject constructor(
    @ApplicationContext private val context: Context
) : CamaraManager {

    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    private val cameraId: String? by lazy {
        try {
            cameraManager.cameraIdList.firstOrNull()
        } catch (e: Exception) {
            null
        }
    }

    private var cameraDevice: CameraDevice? = null
    private var captureSession: CameraCaptureSession? = null
    private var imageReader: ImageReader? = null

    private val backgroundThread = HandlerThread("CameraBackground").also { it.start() }
    private val backgroundHandler = Handler(backgroundThread.looper)

    override fun takePhoto(onSuccess: (ByteArray) -> Unit, onError: (Exception) -> Unit) {
        val id = cameraId ?: return onError(Exception("No camera found"))

        imageReader = ImageReader.newInstance(1920, 1080, ImageFormat.JPEG, 1).apply {
            setOnImageAvailableListener({ reader ->
                val image = reader.acquireLatestImage()
                val buffer = image.planes[0].buffer
                val bytes = ByteArray(buffer.remaining())
                buffer.get(bytes)
                image.close()
                onSuccess(bytes)
            }, backgroundHandler)
        }

        try {
            cameraManager.openCamera(id, object : CameraDevice.StateCallback() {
                override fun onOpened(camera: CameraDevice) {
                    cameraDevice = camera
                    capturePhoto(camera, onError)
                }

                override fun onDisconnected(camera: CameraDevice) {
                    camera.close()
                    cameraDevice = null
                }

                override fun onError(camera: CameraDevice, error: Int) {
                    camera.close()
                    cameraDevice = null
                    onError(Exception("Camera error: $error"))
                }
            }, backgroundHandler)
        } catch (e: SecurityException) {
            onError(Exception("Camera permission not granted"))
        } catch (e: CameraAccessException) {
            onError(e)
        }
    }

    private fun capturePhoto(camera: CameraDevice, onError: (Exception) -> Unit) {
        val surface = imageReader?.surface ?: return

        try {
            camera.createCaptureSession(
                listOf(surface),
                object : CameraCaptureSession.StateCallback() {
                    override fun onConfigured(session: CameraCaptureSession) {
                        captureSession = session
                        val request = camera.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE).apply {
                            addTarget(surface)
                            set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE)
                            set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH)
                        }.build()

                        session.capture(request, null, backgroundHandler)
                    }

                    override fun onConfigureFailed(session: CameraCaptureSession) {
                        onError(Exception("Capture session configuration failed"))
                    }
                },
                backgroundHandler
            )
        } catch (e: CameraAccessException) {
            onError(e)
        }
    }

    override fun release() {
        captureSession?.close()
        captureSession = null
        cameraDevice?.close()
        cameraDevice = null
        imageReader?.close()
        imageReader = null
        backgroundThread.quitSafely()
    }
}