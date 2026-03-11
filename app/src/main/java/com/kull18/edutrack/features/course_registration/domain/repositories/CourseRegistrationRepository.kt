package com.kull18.edutrack.features.course_registration.domain.repositories

import com.kull18.edutrack.features.course_registration.domain.entities.RegistrationCourse

interface CourseRegistrationRepository {
    suspend fun getAvailableCourses(): List<RegistrationCourse>
    suspend fun enrollInCourse(courseId: Int): String
}

