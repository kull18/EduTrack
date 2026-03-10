package com.kull18.edutrack.features.lesson.presentation.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Save
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.kull18.edutrack.core.hardware.data.AndroidGaleriaManager
import com.kull18.edutrack.features.lesson.domain.entities.Leccion
import com.kull18.edutrack.features.lesson.presentation.viewmodels.EditLessonViewModel

private val PrimaryBlue = Color(0xFF3D5AFE)
private val BackgroundGray = Color(0xFFF8F9FA)
private val TextPrimary = Color(0xFF1A1A2E)
private val TextSecondary = Color(0xFF6B7280)
private val BorderColor = Color(0xFFE5E7EB)
private val RedDelete = Color(0xFFEF4444)
private val RedLight = Color(0xFFFFEBEB)

// ─────────────────────────────────────────────────────────
// EDIT LESSON SCREEN
// ─────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditLessonScreen(
    cursoId: Int,
    leccion: Leccion,
    onBackClick: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: EditLessonViewModel = hiltViewModel()
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

    // ─── Registrar launcher y prellenar formulario al entrar ──
    LaunchedEffect(leccion) {
        androidGaleriaManager?.launcher = galleryLauncher
        viewModel.prepareEdit(cursoId, leccion)
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
                        "Editar Lección",
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
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onBackClick,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Cancelar", color = TextPrimary)
                    }
                    Button(
                        onClick = { viewModel.updateLeccion() },
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
                            Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.White)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Guardar cambios", color = Color.White)
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
            // ─── Detalles básicos ─────────────────────────
            EditSectionCard(title = "T  DETALLES BÁSICOS") {
                Text("Título de la Lección", fontSize = 12.sp, color = TextSecondary)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = titulo,
                    onValueChange = viewModel::onTituloChange,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = editFieldColors()
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("#  Orden", fontSize = 12.sp, color = TextSecondary)
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = orden?.toString() ?: "",
                            onValueChange = { viewModel.onOrdenChange(it.toIntOrNull()) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = editFieldColors()
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("🕐  Duración (min)", fontSize = 12.sp, color = TextSecondary)
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = duracion?.toString() ?: "",
                            onValueChange = { viewModel.onDuracionChange(it.toIntOrNull()) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = editFieldColors()
                        )
                    }
                }
            }

            // ─── Contenido ────────────────────────────────
            EditSectionCard(title = "≡  CONTENIDO DEL CURSO") {
                OutlinedTextField(
                    value = contenido,
                    onValueChange = viewModel::onContenidoChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 6,
                    colors = editFieldColors()
                )
            }

            // ─── Imagen de portada ────────────────────────
            EditSectionCard(
                title = "🖼  IMAGEN DE PORTADA",
                action = {
                    if (imagenUri != null || leccion.imagenUrl != null) {
                        TextButton(onClick = { viewModel.onImagenEliminada() }) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                tint = RedDelete,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Eliminar imagen", fontSize = 12.sp, color = RedDelete)
                        }
                    }
                }
            ) {
                // Preview — nueva imagen o la existente del servidor
                val imageSource = imagenUri ?: leccion.imagenUrl?.let { Uri.parse(it) }
                if (imageSource != null) {
                    AsyncImage(
                        model = imageSource,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(BackgroundGray)
                            .border(1.dp, BorderColor, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Sin imagen", color = TextSecondary, fontSize = 13.sp)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    // Cámara — AndroidCamaraManager via ViewModel
                    OutlinedButton(
                        onClick = { viewModel.tomarFoto(context) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                    ) {
                        Text("📷  Tomar Foto", fontSize = 13.sp, color = TextPrimary)
                    }

                    // Galería — ViewModel llama pickImage() → AndroidGaleriaManager
                    // usa el launcher registrado → callback onImagePicked → ViewModel
                    OutlinedButton(
                        onClick = { viewModel.abrirGaleria(context) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                    ) {
                        Text("🖼  De Galería", fontSize = 13.sp, color = TextPrimary)
                    }
                }
            }
        }
    }
}

// ─── Composables auxiliares ────────────────────────────────

@Composable
private fun EditSectionCard(
    title: String,
    action: @Composable RowScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(shape = RoundedCornerShape(16.dp), color = Color.White, shadowElevation = 1.dp) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                action()
            }
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun editFieldColors() = OutlinedTextFieldDefaults.colors(
    unfocusedBorderColor = Color(0xFFE5E7EB),
    focusedBorderColor = Color(0xFF3D5AFE),
    unfocusedContainerColor = Color(0xFFF8F9FA),
    focusedContainerColor = Color.White
)

// ─────────────────────────────────────────────────────────
// DELETE LESSON DIALOG
// ─────────────────────────────────────────────────────────
@Composable
fun DeleteLessonDialog(
    lessonTitle: String,
    onConfirmDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(20.dp), color = Color.White, shadowElevation = 8.dp) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(RedLight, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("!", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = RedDelete)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "¿Eliminar Lección?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Surface(shape = RoundedCornerShape(8.dp), color = BackgroundGray) {
                    Text(
                        text = "\"$lessonTitle\"",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Esta acción es irreversible y se perderán todos los datos asociados.",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onConfirmDelete,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RedDelete)
                ) {
                    Text(
                        "🗑  Eliminar Lección",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
                    Text("Cancelar", fontSize = 15.sp, color = TextSecondary)
                }
            }
        }
    }
}