package com.kull18.edutrack.features.courses_list_instructor.domain.repositories

import com.kull18.edutrack.features.courses_list_instructor.domain.entities.Course

interface CourseRepository {

    suspend fun getAllCourses(): List<Course>
}