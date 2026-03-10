package com.kull18.edutrack.features.lesson.presentation.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.kull18.edutrack.core.hardware.data.AndroidGaleriaManager
import com.kull18.edutrack.features.lesson.presentation.viewmodels.CreateLessonViewModel
import java.io.File

private val PrimaryBlue = Color(0xFF3D5AFE)
private val LightBlue = Color(0xFFE8EEFF)
private val BackgroundGray = Color(0xFFF8F9FA)
private val TextPrimary = Color(0xFF1A1A2E)
private val TextSecondary = Color(0xFF6B7280)
private val BorderColor = Color(0xFFE5E7EB)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateLessonScreen(
    cursoId: Int,
    onBackClick: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: CreateLessonViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val titulo by viewModel.titulo.collectAsStateWithLifecycle()
    val contenido by viewModel.contenido.collectAsStateWithLifecycle()
    val orden by viewModel.orden.collectAsStateWithLifecycle()
    val duracion by viewModel.duracionMinutos.collectAsStateWithLifecycle()
    val imagenUri by viewModel.imagenUri.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    // ─── Obtener AndroidGaleriaManager para registrar launcher ──
    val androidGaleriaManager = remember {
        viewModel.getGaleriaManager() as? AndroidGaleriaManager
    }

    // ─── Launcher galería — se registra en AndroidGaleriaManager ──
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        androidGaleriaManager?.onImagePicked(uri)
    }

    // ─── Registrar launcher y cursoId al entrar ──────────────
    LaunchedEffect(Unit) {
        androidGaleriaManager?.launcher = galleryLauncher
        viewModel.setCursoId(cursoId)
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            viewModel.resetSuccess()
            onSuccess()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Crear Lección",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Surface(color = Color.White, shadowElevation = 8.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onBackClick,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Cancelar", color = TextPrimary)
                    }
                    Button(
                        onClick = { viewModel.createLeccion() },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                        enabled = !uiState.isLoading
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(18.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Guardar Lección", color = Color.White)
                        }
                    }
                }
            }
        },
        containerColor = BackgroundGray
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ─── Título ───────────────────────────────────
            FieldLabel("T  Título de la Lección *")
            OutlinedTextField(
                value = titulo,
                onValueChange = viewModel::onTituloChange,
                placeholder = { Text("Ej. Introducción a React", color = TextSecondary) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = fieldColors()
            )

            // ─── Contenido ────────────────────────────────
            FieldLabel("≡  Contenido *")
            OutlinedTextField(
                value = contenido,
                onValueChange = viewModel::onContenidoChange,
                placeholder = { Text("Escribe los conceptos principales...", color = TextSecondary) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                shape = RoundedCornerShape(12.dp),
                maxLines = 6,
                colors = fieldColors()
            )

            // ─── Orden y Duración ─────────────────────────
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    FieldLabel("#  Orden")
                    OutlinedTextField(
                        value = orden?.toString() ?: "",
                        onValueChange = { viewModel.onOrdenChange(it.toIntOrNull()) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = fieldColors()
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    FieldLabel("🕐  Duración (min)")
                    OutlinedTextField(
                        value = duracion?.toString() ?: "",
                        onValueChange = { viewModel.onDuracionChange(it.toIntOrNull()) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = fieldColors()
                    )
                }
            }

            // ─── Material Visual ──────────────────────────
            FieldLabel("🖼  Material Visual")

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.5.dp, BorderColor, RoundedCornerShape(12.dp))
                    .background(BackgroundGray),
                contentAlignment = Alignment.Center
            ) {
                if (imagenUri != null) {
                    AsyncImage(
                        model = imagenUri,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.Image,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "No hay imagen seleccionada",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary
                        )
                        Text(
                            "Añade una portada para esta lección",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }
                }
            }

            // ─── Botones cámara / galería ─────────────────
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                // Cámara — AndroidCamaraManager via ViewModel
                OutlinedButton(
                    onClick = { viewModel.tomarFoto(context) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                ) {
                    Text("📷  Tomar Foto", color = TextPrimary)
                }

                // Galería — ViewModel llama pickImage() → AndroidGaleriaManager
                // usa el launcher registrado → callback onImagePicked → ViewModel
                OutlinedButton(
                    onClick = { viewModel.abrirGaleria(context) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                ) {
                    Text("🖼  Galería", color = TextPrimary)
                }
            }

            // ─── Tip informativo ──────────────────────────
            Surface(shape = RoundedCornerShape(12.dp), color = LightBlue) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text("✅", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Las lecciones con imágenes tienen un 25% más de compromiso por parte de los alumnos.",
                        fontSize = 12.sp,
                        color = PrimaryBlue,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun FieldLabel(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color = PrimaryBlue
    )
}

@Composable
private fun fieldColors() = OutlinedTextFieldDefaults.colors(
    unfocusedBorderColor = Color(0xFFE5E7EB),
    focusedBorderColor = Color(0xFF3D5AFE),
    unfocusedContainerColor = Color.White,
    focusedContainerColor = Color.White
)

// ─── Helper Bitmap → File ──────────────────────────────────
fun bitmapToFile(context: Context, bitmap: android.graphics.Bitmap): File {
    val file = File(context.cacheDir, "camara_${System.currentTimeMillis()}.jpg")
    file.outputStream().use { bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 90, it) }
    return file
}