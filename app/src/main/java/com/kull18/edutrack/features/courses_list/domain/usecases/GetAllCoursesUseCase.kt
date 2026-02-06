package com.kull18.edutrack.features.courses_list.domain.usecases

import com.kull18.edutrack.features.courses_list.domain.entities.Course
import com.kull18.edutrack.features.courses_list.domain.repositories.CourseRepository

class GetAllCoursesUseCase(
    private val repository: CourseRepository
) {

    suspend operator fun invoke(): Result<List<Course>> {
        return try {
            val courses = repository.getAllCourses()
            Result.success(courses)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
