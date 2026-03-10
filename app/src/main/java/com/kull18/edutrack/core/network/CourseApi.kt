package com.kull18.edutrack.core.network

import com.kull18.edutrack.features.course_create.data.datasources.models.CourseActionRequest
import com.kull18.edutrack.features.course_create.data.datasources.models.CourseActionResponse
import com.kull18.edutrack.features.course_delete.data.datasources.models.CourseDeleteDTO
import com.kull18.edutrack.features.course_detail.data.datasources.models.CourseDetailDTO
import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseEditRequest
import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseEditResponse
import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseGetByIdDTO
import com.kull18.edutrack.features.courses_list.data.datasources.models.CourseDTO
import com.kull18.edutrack.features.login.data.datasources.models.LoginRequest
import com.kull18.edutrack.features.login.data.datasources.models.LoginResponse
import com.kull18.edutrack.features.register.data.datasources.models.RegisterRequest
import com.kull18.edutrack.features.register.data.datasources.models.RegisterResponse

// --- Modelos nuevos F02: Lecciones ---
import com.kull18.edutrack.features.lesson_create.data.datasources.models.CreateLeccionRequest
import com.kull18.edutrack.features.lesson_create.data.datasources.models.LeccionResponse
import com.kull18.edutrack.features.lesson_edit.data.datasources.models.UpdateLeccionRequest
import com.kull18.edutrack.features.lesson_list.data.datasources.models.LeccionDTO

// --- Modelos nuevos F03: Inscripciones ---
import com.kull18.edutrack.features.enrollment.data.datasources.models.InscripcionRequest
import com.kull18.edutrack.features.enrollment.data.datasources.models.InscripcionResponse
import com.kull18.edutrack.features.enrollment.data.datasources.models.ProgresoRequest
import com.kull18.edutrack.features.enrollment.data.datasources.models.ProgresoResponse
import com.kull18.edutrack.features.enrollment.data.datasources.models.InscripcionDetalleDTO
import com.kull18.edutrack.features.lesson.data.datasources.models.LeccionDTO
import com.kull18.edutrack.features.lesson.data.datasources.models.LeccionResponse
import com.kull18.edutrack.features.lesson.data.datasources.models.UpdateLeccionRequest

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface CourseApi {

    // ─────────────────────────────────────────
    // AUTH
    // ─────────────────────────────────────────

    @POST("api/auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("api/auth/register")
    suspend fun registerUser(
        @Body user: RegisterRequest
    ): RegisterResponse

    // ─────────────────────────────────────────
    // CURSOS (F01 — ya existente)
    // ─────────────────────────────────────────

    @GET("api/cursos")
    suspend fun getAllCourses(
        @Header("Authorization") token: String
    ): List<CourseDTO>

    @POST("api/cursos")
    suspend fun createCourse(
        @Header("Authorization") token: String,
        @Body course: CourseActionRequest
    ): CourseActionResponse

    @GET("api/cursos/{id}")
    suspend fun getCourseById(
        @Header("Authorization") token: String,
        @Path("id") courseId: Int
    ): CourseGetByIdDTO

    @GET("api/cursos/{id}")
    suspend fun getCourseByIdDetail(
        @Header("Authorization") token: String,
        @Path("id") courseId: Int
    ): CourseDetailDTO

    @PUT("api/cursos/{id}")
    suspend fun updateCourse(
        @Header("Authorization") token: String,
        @Path("id") courseId: Int,
        @Body course: CourseEditRequest
    ): CourseEditResponse

    @DELETE("api/cursos/{id}")
    suspend fun deleteCourse(
        @Header("Authorization") token: String,
        @Path("id") courseId: Int
    ): CourseDeleteDTO

    // ─────────────────────────────────────────
    // LECCIONES (F02 — nuevo)
    // ─────────────────────────────────────────

    // Crear lección con imagen (multipart/form-data)
    @Multipart
    @POST("api/cursos/{cursoId}/lecciones")
    suspend fun createLeccion(
        @Header("Authorization") token: String,
        @Path("cursoId") cursoId: Int,
        @Part("titulo") titulo: RequestBody,
        @Part("contenido") contenido: RequestBody,
        @Part("orden") orden: RequestBody,
        @Part("duracion_minutos") duracionMinutos: RequestBody,
        @Part imagen: MultipartBody.Part?        // opcional
    ): LeccionResponse

    // Obtener todas las lecciones de un curso
    @GET("api/cursos/{cursoId}/lecciones")
    suspend fun getLeccionesByCurso(
        @Header("Authorization") token: String,
        @Path("cursoId") cursoId: Int
    ): List<LeccionDTO>

    // Obtener detalle de una lección
    @GET("api/cursos/{cursoId}/lecciones/{leccionId}")
    suspend fun getLeccionById(
        @Header("Authorization") token: String,
        @Path("cursoId") cursoId: Int,
        @Path("leccionId") leccionId: Int
    ): LeccionDTO

    // Editar lección (solo texto, sin imagen)
    @PUT("api/cursos/{cursoId}/lecciones/{leccionId}")
    suspend fun updateLeccion(
        @Header("Authorization") token: String,
        @Path("cursoId") cursoId: Int,
        @Path("leccionId") leccionId: Int,
        @Body leccion: UpdateLeccionRequest
    ): LeccionResponse

    // Eliminar lección
    @DELETE("api/cursos/{cursoId}/lecciones/{leccionId}")
    suspend fun deleteLeccion(
        @Header("Authorization") token: String,
        @Path("cursoId") cursoId: Int,
        @Path("leccionId") leccionId: Int
    ): LeccionResponse

    // ─────────────────────────────────────────
    // INSCRIPCIONES (F03 — nuevo)
    // ─────────────────────────────────────────

    // Inscribirse a un curso
    @POST("api/inscripciones")
    suspend fun inscribirse(
        @Header("Authorization") token: String,
        @Body request: InscripcionRequest       // { "curso_id": 1 }
    ): InscripcionResponse

    // Mis inscripciones (alumno)
    @GET("api/inscripciones/mis-inscripciones")
    suspend fun getMisInscripciones(
        @Header("Authorization") token: String
    ): List<InscripcionDetalleDTO>

    // Detalle de una inscripción
    @GET("api/inscripciones/{id}")
    suspend fun getInscripcionById(
        @Header("Authorization") token: String,
        @Path("id") inscripcionId: Int
    ): InscripcionDetalleDTO

    // Marcar/desmarcar lección como completada
    @POST("api/inscripciones/{id}/progreso")
    suspend fun updateProgreso(
        @Header("Authorization") token: String,
        @Path("id") inscripcionId: Int,
        @Body request: ProgresoRequest          // { "leccion_id": 1, "completada": true }
    ): ProgresoResponse

    // Obtener progreso de una inscripción
    @GET("api/inscripciones/{id}/progreso")
    suspend fun getProgreso(
        @Header("Authorization") token: String,
        @Path("id") inscripcionId: Int
    ): ProgresoResponse
