package com.kull18.edutrack.features.course_delete.domain.repositories

import com.kull18.edutrack.features.course_delete.domain.entities.CourseDelete

interface CourseDeleteRepository {
    suspend fun deleteCourse(id: Int): CourseDelete
}