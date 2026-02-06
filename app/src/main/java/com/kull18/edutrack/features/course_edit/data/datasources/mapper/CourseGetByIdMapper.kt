package com.kull18.edutrack.features.course_edit.data.datasources.mapper

import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseGetByIdDTO
import com.kull18.edutrack.features.course_edit.domain.entities.Course


fun CourseGetByIdDTO.toDomain(): Course {
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