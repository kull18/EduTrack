// presentation/screens/CoursesScreen.kt
package com.kull18.edutrack.features.courses_list.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kull18.edutrack.features.courses_list.presentation.components.organims.CoursesTopBar
import com.kull18.edutrack.features.courses_list.presentation.components.organisms.CourseCard
import com.kull18.edutrack.features.courses_list.presentation.viewmodels.CourseListViewModel


@Composable
fun CoursesScreen(
    onEditClick: (Int) -> Unit = {},
    onViewClick: (Int) -> Unit = {},
    onDeleteClick: (Int, String) -> Unit = { _, _ -> },  // Cambiar firma
    onCreateCourseClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val viewModel: CourseListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    Scaffold(
        topBar = {
            CoursesTopBar(title = "Mis Cursos")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateCourseClick,
                containerColor = Color(0xFF6366F1)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Crear curso",
                    tint = Color.White
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.courses.isEmpty() && !uiState.isLoading -> {
                    EmptyCoursesView(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(uiState.courses) { course ->
                            CourseCard(
                                title = course.nombre,
                                isActive = course.activo,
                                onEditClick = { onEditClick(course.id) },
                                onViewClick = { onViewClick(course.id) },
                                onDeleteClick = { onDeleteClick(course.id, course.nombre) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyCoursesView(
    modifier: Modifier = Modifier
) {
    Text(
        text = "No hay cursos disponibles",
        modifier = modifier,
        color = Color.Gray
    )
}