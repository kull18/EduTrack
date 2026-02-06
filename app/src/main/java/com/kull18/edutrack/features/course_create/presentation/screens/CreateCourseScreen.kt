package com.example.app.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ui.organisms.CourseFormOrganism
import com.example.app.ui.organisms.TopBarOrganism
import com.kull18.edutrack.core.ui.components.MainScaffold
import com.kull18.edutrack.features.course_create.presentation.viewmodels.CreateCourseViewModel
import com.kull18.edutrack.features.course_create.presentation.viewmodels.CreateCourseViewModelFactory

@Composable
fun CreateCourseScreen(
    factory: CreateCourseViewModelFactory,
    onBackClick: () -> Unit,
    onSuccess: () -> Unit
) {
    val viewModel: CreateCourseViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val courseName by viewModel.courseName.collectAsStateWithLifecycle()
    val description by viewModel.courseDescription.collectAsStateWithLifecycle()
    val duration by viewModel.courseDuration.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onSuccess()
        }
    }

    MainScaffold(
        topAppBar = { TopBarOrganism("Crear curso", onBackClick = onBackClick) },
        snackbackHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        CourseFormOrganism(
            courseName = courseName,
            onCourseNameChange = viewModel::onCourseNameChange,
            description = description,
            onDescriptionChange = viewModel::onCourseDescriptionChange,
            duration = duration?.toString() ?: "",
            onDurationChange = {
                viewModel.onCourseDurationChange(it.trim().toIntOrNull())
            },
            onCancel = onBackClick,
            onSave = { viewModel.createCourse() },
            isLoading = uiState.isLoading,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}