// screens/EditCourseScreen.kt
package com.kull18.edutrack.features.course_edit.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kull18.edutrack.core.ui.components.MainScaffold
import com.kull18.edutrack.features.course_edit.presentation.components.organims.CoursesTopBar
import com.kull18.edutrack.features.course_edit.presentation.components.organisms.EditCourseFormOrganism


@Composable
fun EditCourseScreen(
    courseId: Int,
    onBackClick: () -> Unit,
    onSuccess: () -> Unit
) {
    val viewModel: EditCourseViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val courseName by viewModel.courseName.collectAsStateWithLifecycle()
    val description by viewModel.courseDescription.collectAsStateWithLifecycle()
    val duration by viewModel.courseDuration.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(courseId) {
        viewModel.loadCourse(courseId)
    }

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
        topAppBar = {
            CoursesTopBar(
                title = "Editar Curso",
                onBackClick = onBackClick
            )
        },
        snackbackHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        EditCourseFormOrganism(
            courseName = courseName,
            onCourseNameChange = viewModel::onCourseNameChange,
            description = description,
            onDescriptionChange = viewModel::onCourseDescriptionChange,
            duration = duration?.toString() ?: "",
            onDurationChange = {
                viewModel.onCourseDurationChange(it.toIntOrNull())
            },
            onCancel = onBackClick,
            onSave = { viewModel.updateCourse() },
            isLoading = uiState.isLoading,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}