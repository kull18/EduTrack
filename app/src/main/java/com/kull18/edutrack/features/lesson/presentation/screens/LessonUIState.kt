package com.kull18.edutrack.features.lesson.presentation.screens

import com.kull18.edutrack.features.lesson.domain.entities.Leccion

data class LessonUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,

    // Lista de lecciones (LessonListScreen)
    val lecciones: List<Leccion> = emptyList(),

    // Lección seleccionada (detalle / editar)
    val leccionSeleccionada: Leccion? = null,

    // Flag para mostrar el diálogo de eliminar
    val showDeleteDialog: Boolean = false,
    val leccionAEliminar: Leccion? = null
)