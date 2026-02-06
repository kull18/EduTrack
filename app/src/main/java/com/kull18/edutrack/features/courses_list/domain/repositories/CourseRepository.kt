package com.kull18.edutrack.features.courses_list.domain.repositories

import com.kull18.edutrack.features.courses_list.domain.entities.Course

interface CourseRepository {

    suspend fun getAllCourses(): List<Course>
}