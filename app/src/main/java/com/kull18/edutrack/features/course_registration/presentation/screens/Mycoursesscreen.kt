package com.kull18.edutrack.features.course_registration.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kull18.edutrack.presentation.navigation.EduTrackBottomBar

private val PrimaryBlue = Color(0xFF3D5AFE)
private val LightBlue = Color(0xFFE8EEFF)
private val BackgroundGray = Color(0xFFF8F9FA)
private val TextPrimary = Color(0xFF1A1A2E)
private val TextSecondary = Color(0xFF6B7280)
private val GreenSuccess = Color(0xFF4CAF50)

data class MyCourseUiModel(
    val id: Int,
    val name: String,
    val instructorName: String,
    val instructorImageUrl: String?,
    val progressPercentage: Float,
    val status: String, // "activo" | "completado"
    val lastAccessText: String,
    val hasCertificate: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCoursesScreen(
    navController: NavHostController,
    onCourseClick: (MyCourseUiModel) -> Unit = {},
    onExploreCatalogClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedFilter by remember { mutableStateOf("Todos") }
    val filters = listOf("Todos", "Activos")

    // Datos de prueba — reemplazar con ViewModel
    val courses = remember {
        listOf(
            MyCourseUiModel(1, "Desarrollo Web Fullstack con React y Node.js", "Dr. Alejandro Méndez", null, 0.65f, "activo", "HOY, 10:30 AM"),
            MyCourseUiModel(2, "Fundamentos de Diseño UI/UX para Aplicaciones Móviles", "Sra. Elena Rodríguez", null, 1.0f, "completado", "AYER, 18:45 PM", hasCertificate = true),
            MyCourseUiModel(3, "Introducción a la Inteligencia Artificial y Machine Learning", "Ing. Roberto Gómez", null, 0.12f, "activo", "24 OCT, 2023"),
            MyCourseUiModel(4, "Marketing Digital y Estrategia de Contenidos", "Lic. Patricia Luna", null, 0.45f, "activo", "20 OCT, 2023"),
        )
    }

    val filteredCourses = if (selectedFilter == "Todos") courses
    else courses.filter { it.status == "activo" }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mis Cursos",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.FilterList, contentDescription = "Filtrar", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = { EduTrackBottomBar(navController = navController) },
        containerColor = BackgroundGray
    ) { paddingValues ->

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // Header
            item {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Tu Aprendizaje",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                            Text(
                                text = "Tienes ${filteredCourses.size} cursos en curso",
                                fontSize = 13.sp,
                                color = TextSecondary,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                        // Filtros
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            filters.forEach { filter ->
                                val isSelected = filter == selectedFilter
                                Surface(
                                    shape = RoundedCornerShape(20.dp),
                                    color = if (isSelected) PrimaryBlue else BackgroundGray,
                                    modifier = Modifier.clickable { selectedFilter = filter }
                                ) {
                                    Text(
                                        text = filter,
                                        fontSize = 12.sp,
                                        color = if (isSelected) Color.White else TextSecondary,
                                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            // Lista de cursos
            items(filteredCourses) { course ->
                MyCourseItemCard(
                    course = course,
                    onClick = { onCourseClick(course) },
                    modifier = Modifier
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
                HorizontalDivider(color = Color(0xFFF3F4F6), thickness = 1.dp)
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Card explorar catálogo
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = LightBlue
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(PrimaryBlue.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("📚", fontSize = 22.sp)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "¿Buscas nuevos retos?",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Explora nuestro catálogo y descubre cursos adaptados a tus metas.",
                            fontSize = 13.sp,
                            color = TextSecondary,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Button(
                            onClick = onExploreCatalogClick,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                        ) {
                            Text("Explorar Catálogo", color = Color.White, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyCourseItemCard(
    course: MyCourseUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isCompleted = course.status == "completado"
    val progressColor = if (isCompleted) GreenSuccess else PrimaryBlue
    val statusColor = if (isCompleted) GreenSuccess else PrimaryBlue

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            // Nombre del curso
            Text(
                text = course.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Instructor
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(BackgroundGray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("👤", fontSize = 10.sp)
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = course.instructorName,
                    fontSize = 12.sp,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Estado
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (isCompleted) "⊙ Completado" else "⊙ Activo",
                    fontSize = 12.sp,
                    color = statusColor,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${(course.progressPercentage * 100).toInt()}%",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Barra de progreso
            LinearProgressIndicator(
                progress = { course.progressPercentage },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = progressColor,
                trackColor = Color(0xFFE5E7EB),
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Última vez + certificado
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "ÚLTIMA VEZ: ${course.lastAccessText}",
                    fontSize = 10.sp,
                    color = TextSecondary,
                    letterSpacing = 0.3.sp
                )
                if (course.hasCertificate) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = GreenSuccess.copy(alpha = 0.1f)
                    ) {
                        Text(
                            text = "Certificado Listo",
                            fontSize = 10.sp,
                            color = GreenSuccess,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            imageVector = Icons.Default.NavigateNext,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}