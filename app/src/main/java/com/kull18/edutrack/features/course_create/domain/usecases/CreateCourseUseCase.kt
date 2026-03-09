package com.kull18.edutrack.features.course_create.domain.usecases

import com.kull18.edutrack.features.course_create.data.datasources.models.CourseActionRequest
import com.kull18.edutrack.features.course_create.domain.entities.CourseCreate
import com.kull18.edutrack.features.course_create.domain.repositories.CreateCourseRepository
import javax.inject.Inject

class CreateCourseUseCase @Inject constructor(
    private val repository: CreateCourseRepository
) {

    suspend operator fun invoke(course: CourseActionRequest): Result<CourseCreate> {
        return try {
            val response = repository.createCourse(course)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
