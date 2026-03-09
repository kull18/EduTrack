// screens/CourseDetailScreen.kt
package com.kull18.edutrack.features.course_detail.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
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
import com.kull18.edutrack.core.ui.components.MainScaffold
import com.kull18.edutrack.features.course_detail.presentation.components.organisms.CourseDetailOrganism
import com.kull18.edutrack.features.courses_list.presentation.components.organims.CoursesTopBar

@Composable
fun CourseDetailScreen(
    courseId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: CourseDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(courseId) {
        viewModel.loadCourseDetail(courseId)
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    MainScaffold(
        topAppBar = {
            CoursesTopBar(
                title = uiState.course?.nombre ?: "Detalle del Curso",
                onBackClick = onBackClick
            )
        },
        snackbackHost = {
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

                uiState.course != null -> {
                    CourseDetailOrganism(
                        course = uiState.course!!,
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    )
                }

                else -> {
                    Text(
                        text = "No se encontró el curso",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Gray
                    )
                }
            }
        }
    }
}