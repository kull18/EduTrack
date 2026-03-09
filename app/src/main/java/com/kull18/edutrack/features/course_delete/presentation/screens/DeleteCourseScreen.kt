// screens/DeleteCourseScreen.kt
package com.kull18.edutrack.features.course_delete.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kull18.edutrack.core.ui.components.MainScaffold
import com.kull18.edutrack.features.course_delete.presentation.components.organisms.DeleteCourseOrganism
import com.kull18.edutrack.features.courses_list.presentation.components.organims.CoursesTopBar

@Composable
fun DeleteCourseScreen(
    courseId: Int,
    courseName: String,
    onBackClick: () -> Unit,
    onDeleteSuccess: () -> Unit
) {
    val viewModel: DeleteCourseViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onDeleteSuccess()
        }
    }

    MainScaffold(
        topAppBar = {
            CoursesTopBar(
                title = "Eliminar Curso",
                onBackClick = onBackClick
            )
        },
        snackbackHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
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

                else -> {
                    DeleteCourseOrganism(
                        courseName = courseName,
                        onConfirm = { viewModel.deleteCourse(courseId) },
                        onCancel = onBackClick,
                        isLoading = uiState.isLoading
                    )
                }
            }
        }
    }
}