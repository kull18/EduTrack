package com.kull18.edutrack.features.course_registration.data.datasources.mapper

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

