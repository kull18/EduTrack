package com.kull18.edutrack.core.network

import com.kull18.edutrack.features.courses_list_instructor.data.datasources.models.CourseActionResponse
import com.kull18.edutrack.features.courses_list_instructor.data.datasources.models.CourseDTO
import com.kull18.edutrack.features.courses_list_instructor.data.datasources.models.CourseRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface CourseApi {

    @GET("api/cursos")
    suspend fun getAllCourses(
        @Header("Authorization") token: String
    ): List<CourseDTO>

    @POST("api/cursos")
    suspend fun createCourse(
        @Header("Authorization") token: String,
        @Body course: CourseRequest
    ): CourseActionResponse

    @GET("api/cursos/{id}")
    suspend fun getCourseById(
        @Header("Authorization") token: String,
        @Path("id") courseId: Int
    ): CourseDTO

    @PUT("api/cursos/{id}")
    suspend fun updateCourse(
        @Header("Authorization") token: String,
        @Path("id") courseId: Int,
        @Body course: CourseRequest
    ): CourseActionResponse

    @DELETE("api/cursos/{id}")
    suspend fun deleteCourse(
        @Header("Authorization") token: String,
        @Path("id") courseId: Int
    ): Unit

    @PATCH("api/cursos/{id}/toggle-activo")
    suspend fun toggleCourseActive(
        @Header("Authorization") token: String,
        @Path("id") courseId: Int
    ): CourseActionResponse
}
