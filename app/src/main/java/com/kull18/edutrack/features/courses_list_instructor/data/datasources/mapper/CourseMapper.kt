package com.kull18.edutrack.features.courses_list_instructor.data.datasources.mapper

import com.kull18.edutrack.features.courses_list_instructor.data.datasources.models.CourseDTO
import com.kull18.edutrack.features.courses_list_instructor.domain.entities.Course

fun CourseDTO.toDomain(): Course {
    return Course(
        id = this.id,
        nombre = this.nombre,
        descripcion = this.descripcion,
        duracionHoras = this.duracionHoras,
        instructorId = this.instructorId,
        instructor = this.instructor,
        activo = this.activo,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}