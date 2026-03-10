package com.kull18.edutrack.features.lesson.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kull18.edutrack.features.lesson.domain.entities.Leccion
import com.kull18.edutrack.features.lesson.presentation.components.LessonItemCard
import com.kull18.edutrack.features.lesson.presentation.viewmodels.LessonListViewModel

private val PrimaryBlue = Color(0xFF3D5AFE)
private val BackgroundGray = Color(0xFFF8F9FA)
private val TextPrimary = Color(0xFF1A1A2E)
private val TextSecondary = Color(0xFF6B7280)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonListScreen(
    cursoId: Int,
    courseName: String,
    onBackClick: () -> Unit,
    onAddLessonClick: () -> Unit,
    onEditLesson: (Leccion) -> Unit,
    viewModel: LessonListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    // Cargar lecciones al entrar
    LaunchedEffect(cursoId) {
        viewModel.loadLecciones(cursoId)
    }

    // Mostrar errores
    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    // Diálogo de confirmación eliminar
    if (uiState.showDeleteDialog) {
        uiState.leccionAEliminar?.let { leccion ->
            DeleteLessonDialog(
                lessonTitle = leccion.titulo,
                onConfirmDelete = { viewModel.confirmDelete() },
                onDismiss = { viewModel.onDeleteDismiss() }
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = courseName,
                        fontSize = 16.sp,
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddLessonClick,
                containerColor = PrimaryBlue,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar lección", tint = Color.White)
            }
        },
        containerColor = BackgroundGray
    ) { paddingValues ->

        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryBlue)
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Stats del curso
            item {
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StatBox(
                        label = "LECCIONES",
                        value = uiState.lecciones.size.toString(),
                        modifier = Modifier.weight(1f)
                    )
                    StatBox(
                        label = "DURACIÓN",
                        value = "${uiState.lecciones.sumOf { it.duracionMinutos }} min",
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Header lista
            item {
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lecciones del Programa",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = "Orden Cronológico",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }

            if (uiState.lecciones.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("📚", fontSize = 40.sp)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Aún no hay lecciones",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextPrimary
                            )
                            Text(
                                text = "Presiona + para agregar la primera",
                                fontSize = 13.sp,
                                color = TextSecondary
                            )
                        }
                    }
                }
            } else {
                items(uiState.lecciones.sortedBy { it.orden }) { leccion ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    ) {
                        LessonItemCard(
                            order = leccion.orden,
                            title = leccion.titulo,
                            durationMinutes = leccion.duracionMinutos,
                            onEditClick = { onEditLesson(leccion) },
                            onDeleteClick = { viewModel.onDeleteClick(leccion) },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatBox(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = BackgroundGray
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
            Text(text = label, fontSize = 10.sp, color = TextSecondary)
        }
    }
}