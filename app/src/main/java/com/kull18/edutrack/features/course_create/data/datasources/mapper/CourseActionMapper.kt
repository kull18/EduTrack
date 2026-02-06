package com.kull18.edutrack.features.course_create.data.datasources.mapper

import com.kull18.edutrack.features.course_create.data.datasources.models.CourseDTO
import com.kull18.edutrack.features.course_create.domain.entities.CourseCreate


fun CourseDTO.toDomain(): CourseCreate {
    return CourseCreate(
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