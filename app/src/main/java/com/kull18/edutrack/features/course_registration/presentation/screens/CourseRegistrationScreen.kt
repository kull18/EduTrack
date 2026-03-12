package com.kull18.edutrack.features.course_registration.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kull18.edutrack.features.course_registration.domain.entities.RegistrationCourse
import com.kull18.edutrack.features.course_registration.presentation.viewmodels.CourseRegistrationViewModel

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun CourseRegistrationScreen(
    modifier: Modifier = Modifier,
    onCourseClick: (courseId: Int, courseName: String) -> Unit = { _, _ -> }
) {
    val viewModel: CourseRegistrationViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    // Navigate to lessons after successful enrollment
    LaunchedEffect(uiState.lastEnrolledCourseId) {
        uiState.lastEnrolledCourseId?.let { courseId ->
            val course = uiState.courses.find { it.id == courseId }
            if (course != null) {
                onCourseClick(courseId, course.nombre)
            }
            viewModel.clearLastEnrolled()
        }
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    LaunchedEffect(uiState.successMessage) {
        uiState.successMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearSuccessMessage()
        }
    }

    val filteredCourses by remember(uiState.courses, uiState.selectedCategory, uiState.query) {
        derivedStateOf {
            uiState.courses.filter { course ->
                val matchesCategory = uiState.selectedCategory == "Todos" ||
                    course.nombre.contains(uiState.selectedCategory, ignoreCase = true) ||
                    course.descripcion.contains(uiState.selectedCategory, ignoreCase = true)
                val matchesQuery = uiState.query.isBlank() ||
                    course.nombre.contains(uiState.query, ignoreCase = true) ||
                    course.descripcion.contains(uiState.query, ignoreCase = true)
                matchesCategory && matchesQuery
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(text = "Explora Cursos", fontWeight = FontWeight.Bold)
                        Text(
                            text = "Aprende nuevas habilidades con los mejores instructores.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color(0xFFF4F6FB))
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        OutlinedTextField(
                            value = uiState.query,
                            onValueChange = viewModel::onQueryChange,
                            label = { Text("Buscar cursos, temas...") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Buscar"
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.FilterList,
                                    contentDescription = "Filtrar"
                                )
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                    }

                    item {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(viewModel.categories) { category ->
                                FilterChip(
                                    selected = uiState.selectedCategory == category,
                                    onClick = { viewModel.onCategorySelected(category) },
                                    label = { Text(category) }
                                )
                            }
                        }
                    }

                    if (filteredCourses.isEmpty()) {
                        item {
                            Text(
                                text = "No encontramos cursos con esos filtros.",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(vertical = 20.dp)
                            )
                        }
                    } else {
                        items(filteredCourses, key = { it.id }) { course ->
                            CourseRegistrationCard(
                                course = course,
                                isEnrolled = course.id in uiState.enrolledCourseIds,
                                isEnrolling = course.id in uiState.enrollingCourseIds,
                                onEnrollClick = { viewModel.enroll(course.id) },
                                onCardClick = { onCourseClick(course.id, course.nombre) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
private fun CourseRegistrationCard(
    course: RegistrationCourse,
    isEnrolled: Boolean,
    isEnrolling: Boolean,
    onEnrollClick: () -> Unit,
    onCardClick: () -> Unit
) {
    ElevatedCard(
        onClick = onCardClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFFC7D2FE), Color(0xFFBFDBFE), Color(0xFFBAE6FD))
                        )
                    )
            )

            Text(
                text = course.nombre,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = course.descripcion,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = course.instructorNombre,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "${course.duracionHoras}h",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            if (isEnrolled) {
                Button(onClick = onCardClick, modifier = Modifier.fillMaxWidth()) {
                    Text("Ver Lecciones")
                }
            } else {
                Button(
                    onClick = onEnrollClick,
                    enabled = !isEnrolling,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isEnrolling) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(18.dp)
                                .height(18.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Inscribirme Ahora")
                    }
                }
            }
        }
    }
}

