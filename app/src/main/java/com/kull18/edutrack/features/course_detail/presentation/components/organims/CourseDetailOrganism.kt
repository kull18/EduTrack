// organisms/CourseDetailOrganism.kt
package com.kull18.edutrack.features.course_detail.presentation.components.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kull18.edutrack.features.course_detail.domain.entities.CourseDetial
import com.kull18.edutrack.features.course_detail.presentation.components.molecules.DetailRowMolecule
import com.kull18.edutrack.features.course_detail.presentation.components.molecules.StatusRowMolecule

@Composable
fun CourseDetailOrganism(
    course: CourseDetial,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        DetailRowMolecule(
            icon = Icons.Outlined.MenuBook,
            label = "Nombre",
            value = course.nombre
        )

        Spacer(modifier = Modifier.height(20.dp))

        DetailRowMolecule(
            icon = Icons.Outlined.Description,
            label = "Descripción",
            value = course.descripcion
        )

        Spacer(modifier = Modifier.height(20.dp))

        DetailRowMolecule(
            icon = Icons.Outlined.Schedule,
            label = "Duración",
            value = "${course.duracionHoras} horas"
        )

        Spacer(modifier = Modifier.height(20.dp))

        DetailRowMolecule(
            icon = Icons.Outlined.Person,
            label = "Instructor",
            value = course.instructor?.user?.nombre ?: "No asignado"
        )

        Spacer(modifier = Modifier.height(20.dp))

        StatusRowMolecule(isActive = course.activo)

        Spacer(modifier = Modifier.height(20.dp))

        DetailRowMolecule(
            icon = Icons.Outlined.CalendarToday,
            label = "Creado",
            value = formatDate(course.createdAt)
        )

        Spacer(modifier = Modifier.height(20.dp))

        DetailRowMolecule(
            icon = Icons.Outlined.CalendarToday,
            label = "Última actualización",
            value = formatDate(course.updatedAt)
        )
    }
}

private fun formatDate(dateString: String): String {
    // Formato simple, puedes mejorarlo con SimpleDateFormat o kotlinx.datetime
    return try {
        dateString.split("T").firstOrNull()?.let {
            val parts = it.split("-")
            "${parts[2]} de ${getMonthName(parts[1].toInt())} de ${parts[0]}"
        } ?: dateString
    } catch (e: Exception) {
        dateString
    }
}

private fun getMonthName(month: Int): String {
    return when (month) {
        1 -> "enero"
        2 -> "febrero"
        3 -> "marzo"
        4 -> "abril"
        5 -> "mayo"
        6 -> "junio"
        7 -> "julio"
        8 -> "agosto"
        9 -> "septiembre"
        10 -> "octubre"
        11 -> "noviembre"
        12 -> "diciembre"
        else -> ""
    }
}