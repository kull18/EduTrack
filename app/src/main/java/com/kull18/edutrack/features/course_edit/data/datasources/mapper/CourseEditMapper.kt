package com.kull18.edutrack.features.course_edit.data.datasources.mapper

import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseEditDTO
import com.kull18.edutrack.features.course_edit.domain.entities.CourseEdit

fun CourseEditDTO.toDomain(): CourseEdit {
    return CourseEdit(
        id = this.id,
        nombre = this.nombre,
        descripcion = this.descripcion,
        duracion_horas = this.duracionHoras,
        instructor_id = this.instructorId,
        activo = this.activo,
        created_at = this.createdAt,
        updated_at = this.updatedAt
    )
}