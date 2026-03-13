package com.kull18.edutrack.features.course_registration.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.kull18.edutrack.core.database.dao.CursoDao
import com.kull18.edutrack.core.database.dao.ProgresoLeccionDao
import com.kull18.edutrack.features.course_registration.data.datasources.mapper.buildCursoProgreso
import com.kull18.edutrack.features.course_registration.domain.entities.CursoProgreso
import com.kull18.edutrack.features.course_registration.domain.repositories.MyCourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MyCourseRepositoryImpl @Inject constructor(
    private val progresoDao: ProgresoLeccionDao,
    private val cursoDao: CursoDao
) : MyCourseRepository {

    override fun getProgreso(courseId: Int): Flow<CursoProgreso> =
        combine(
            progresoDao.getProgresoByInscripcion(courseId),
            cursoDao.getCursosInscritos()
        ) { lecciones, cursos ->
            val curso = cursos.find { it.id == courseId }
            buildCursoProgreso(courseId, curso, lecciones)
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun toggleLeccion(leccionId: Int, completada: Boolean) {
        withContext(Dispatchers.IO) {
            val fecha = if (completada)
                DateTimeFormatter.ISO_INSTANT.format(Instant.now())
            else null
            progresoDao.updateCompletada(leccionId, completada, fecha)
        }
    }
}