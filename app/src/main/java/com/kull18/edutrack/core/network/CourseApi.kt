package com.kull18.edutrack.core.network

import com.kull18.edutrack.features.course_create.data.datasources.models.CourseActionRequest
import com.kull18.edutrack.features.course_create.data.datasources.models.CourseActionResponse
import com.kull18.edutrack.features.course_delete.data.datasources.models.CourseDeleteDTO
import com.kull18.edutrack.features.course_detail.data.datasources.models.CourseDetailDTO
import com.kull18.edutrack.features.course_detail.domain.entities.CourseDetial
import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseEditRequest
import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseEditResponse
import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseGetByIdDTO
import com.kull18.edutrack.features.courses_list.data.datasources.models.CourseDTO
import com.kull18.edutrack.features.courses_list.data.datasources.models.CourseRequest
import com.kull18.edutrack.features.login.data.datasources.models.LoginRequest
import com.kull18.edutrack.features.login.data.datasources.models.LoginResponse
import com.kull18.edutrack.features.register.data.datasources.models.RegisterRequest
import com.kull18.edutrack.features.register.data.datasources.models.RegisterResponse
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

    @POST("api/auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("api/auth/register")
    suspend fun registerUser(
        @Body user: RegisterRequest
    ): RegisterResponse
}
