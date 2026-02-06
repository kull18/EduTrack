package com.kull18.edutrack.features.course_delete.data.datasources.mapper

import com.kull18.edutrack.features.course_delete.data.datasources.models.CourseDeleteDTO
import com.kull18.edutrack.features.course_delete.domain.entities.CourseDelete

fun CourseDeleteDTO.toDomain(): CourseDelete {
    return CourseDelete(
        message = this.message
    )
}