package com.kull18.edutrack.features.course_registration.domain.usecases

import com.kull18.edutrack.features.course_registration.domain.repositories.MyCourseRepository
import com.kull18.edutrack.features.course_registration.domain.entities.CursoProgreso
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProgresoDaoUseCase @Inject constructor(
    private val repository: MyCourseRepository
) {
    operator fun invoke(courseId: Int): Flow<Result<CursoProgreso>> =
        repository.getProgreso(courseId)
            .map { Result.success(it) }
            .catch { emit(Result.failure(it)) }
}