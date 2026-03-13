package com.kull18.edutrack.features.course_registration.data.datasources.mapper

import com.kull18.edutrack.core.database.entities.CursoEntity
import com.kull18.edutrack.core.database.entities.ProgresoLeccionEntity
import com.kull18.edutrack.features.course_registration.domain.entities.CursoProgreso
import com.kull18.edutrack.features.course_registration.domain.entities.LeccionProgreso

fun ProgresoLeccionEntity.toDomain(): LeccionProgreso = LeccionProgreso(
    leccionId = this.leccionId,
    inscripcionId = this.inscripcionId,
    titulo = this.titulo,
    completada = this.completada,
    fechaCompletado = this.fechaCompletado
)

fun buildCursoProgreso(
    courseId: Int,
    curso: CursoEntity?,
    lecciones: List<ProgresoLeccionEntity>
): CursoProgreso {
    val domainLecciones = lecciones.map { it.toDomain() }
    val total = domainLecciones.size
    val completadas = domainLecciones.count { it.completada }
    return CursoProgreso(
        courseId = courseId,
        courseName = curso?.nombre ?: "",
        instructorNombre = curso?.instructorNombre ?: "",
        duracionTotalMin = total * 20,
        lecciones = domainLecciones,
        totalLecciones = total,
        completadas = completadas,
        porcentaje = if (total > 0) completadas.toFloat() / total else 0f
    )
}