package com.kull18.edutrack.features.course_registration.data.repositories

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_registration.data.datasources.mapper.toRegistrationDomain
import com.kull18.edutrack.features.course_registration.domain.entities.RegistrationCourse
import com.kull18.edutrack.features.course_registration.domain.repositories.CourseRegistrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CourseRegistrationRepositoryImpl @Inject constructor(
    private val api: CourseApi,
    private val tokenDataStore: TokenDataStore
) : CourseRegistrationRepository {

    override suspend fun getAvailableCourses(): List<RegistrationCourse> {
        return withContext(Dispatchers.IO) {
            val token = tokenDataStore.getToken().first().orEmpty()
            require(token.isNotBlank()) { "Sesion expirada. Inicia sesion nuevamente" }

            api.getAllCourses("Bearer $token")
                .filter { it.activo }
                .map { it.toRegistrationDomain() }
        }
    }

    override suspend fun enrollInCourse(courseId: Int): String {
        return withContext(Dispatchers.IO) {
            val token = tokenDataStore.getToken().first().orEmpty()
            require(token.isNotBlank()) { "Sesion expirada. Inicia sesion nuevamente" }

            api.enrollInCourse("Bearer $token", courseId).message ?: "Inscripcion exitosa"
        }
    }
}

