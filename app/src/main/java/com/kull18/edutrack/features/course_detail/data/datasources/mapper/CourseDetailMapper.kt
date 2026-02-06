package com.kull18.edutrack.features.course_detail.data.datasources.mapper

import com.kull18.edutrack.features.course_detail.data.datasources.models.CourseDetailDTO
import com.kull18.edutrack.features.course_detail.domain.entities.CourseDetial

fun CourseDetailDTO.toDomain(): CourseDetial {
    return CourseDetial(
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