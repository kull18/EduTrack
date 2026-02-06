// Actualizar AppRoutes.kt
package com.kull18.edutrack.presentation.navigation

sealed class AppRoutes(val route: String) {
    object Login : AppRoutes("login")
    object Register : AppRoutes("register")
    object InstructorCourses : AppRoutes("instructor/courses")
    object CreateCourse : AppRoutes("courses/create")
    object StudentDashboard : AppRoutes("student/dashboard")

    object CourseDetail : AppRoutes("courses/detail/{courseId}") {
        fun createRoute(courseId: Int) = "courses/detail/$courseId"
    }

    object EditCourse : AppRoutes("courses/edit/{courseId}") {
        fun createRoute(courseId: Int) = "courses/edit/$courseId"
    }

    object DeleteCourse : AppRoutes("courses/delete/{courseId}/{courseName}") {
        fun createRoute(courseId: Int, courseName: String) =
            "courses/delete/$courseId/${java.net.URLEncoder.encode(courseName, "UTF-8")}"
    }
}