package com.kull18.edutrack.features.course_create.domain.repositories

import com.kull18.edutrack.features.course_create.data.datasources.models.CourseActionRequest
import com.kull18.edutrack.features.course_create.domain.entities.CourseCreate

interface CreateCourseRepository {

    suspend fun createCourse( course: CourseActionRequest): CourseCreate
}