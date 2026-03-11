package com.kull18.edutrack.features.course_registration.data.repositories

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_registration.data.datasources.mapper.toInscripcionDomain
import com.kull18.edutrack.features.course_registration.data.datasources.mapper.toRegistrationDomain
import com.kull18.edutrack.features.course_registration.data.datasources.models.InscripcionRequest
import com.kull18.edutrack.features.course_registration.data.datasources.models.ProgresoRequest
import com.kull18.edutrack.features.course_registration.data.datasources.models.ProgresoResponse
import com.kull18.edutrack.features.course_registration.domain.entities.Inscripcion
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

    private suspend fun requireToken(): String {
        val token = tokenDataStore.getToken().first().orEmpty()
        require(token.isNotBlank()) { "Sesion expirada. Inicia sesion nuevamente" }
        return "Bearer $token"
    }

    override suspend fun getAvailableCourses(): List<RegistrationCourse> {
        return withContext(Dispatchers.IO) {
            val token = requireToken()
            api.getAllCourses(token)
                .filter { it.activo }
                .map { it.toRegistrationDomain() }
        }
    }

    override suspend fun enrollInCourse(courseId: Int): String {
        return withContext(Dispatchers.IO) {
            val token = requireToken()
            val response = api.inscribirse(token, InscripcionRequest(cursoId = courseId))
            response.message ?: "Inscripcion exitosa"
        }
    }

    override suspend fun getMisInscripciones(): List<Inscripcion> {
        return withContext(Dispatchers.IO) {
            val token = requireToken()
            api.getMisInscripciones(token).map { it.toInscripcionDomain() }
        }
    }

    override suspend fun getInscripcionById(inscripcionId: Int): Inscripcion {
        return withContext(Dispatchers.IO) {
            val token = requireToken()
            api.getInscripcionById(token, inscripcionId).toInscripcionDomain()
        }
    }

    override suspend fun updateProgreso(
        inscripcionId: Int,
        leccionId: Int,
        completada: Boolean
    ): ProgresoResponse {
        return withContext(Dispatchers.IO) {
            val token = requireToken()
            api.updateProgreso(token, inscripcionId, ProgresoRequest(leccionId, completada))
        }
    }

    override suspend fun getProgreso(inscripcionId: Int): ProgresoResponse {
        return withContext(Dispatchers.IO) {
            val token = requireToken()
            api.getProgreso(token, inscripcionId)
        }
    }
}

