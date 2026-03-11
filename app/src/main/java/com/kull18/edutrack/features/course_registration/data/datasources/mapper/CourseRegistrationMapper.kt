package com.kull18.edutrack.features.course_registration.data.datasources.mapper

import com.kull18.edutrack.features.course_registration.data.datasources.models.InscripcionDetalleDTO
import com.kull18.edutrack.features.course_registration.domain.entities.Inscripcion
import com.kull18.edutrack.features.course_registration.domain.entities.RegistrationCourse
import com.kull18.edutrack.features.courses_list.data.datasources.models.CourseDTO

fun CourseDTO.toRegistrationDomain(): RegistrationCourse {
    return RegistrationCourse(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        duracionHoras = duracionHoras,
        instructorNombre = instructor?.user?.nombre ?: "Instructor no disponible",
        activo = activo
    )
}

fun InscripcionDetalleDTO.toInscripcionDomain(): Inscripcion {
    return Inscripcion(
        id = id,
        cursoId = cursoId,
        cursoNombre = curso?.nombre ?: "Curso no disponible",
        cursoDescripcion = curso?.descripcion ?: "",
        duracionHoras = curso?.duracionHoras ?: 0,
        instructorNombre = curso?.instructor?.user?.nombre ?: "Instructor no disponible",
        estado = estado ?: "activo",
        progreso = progreso ?: 0.0,
        fechaInscripcion = createdAt ?: ""
    )
}

