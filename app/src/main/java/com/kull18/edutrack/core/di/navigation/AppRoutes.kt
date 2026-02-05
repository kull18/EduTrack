// presentation/navigation/AppRoutes.kt
package com.kull18.edutrack.presentation.navigation

sealed class AppRoutes(val route: String) {
    object Login : AppRoutes("login")
    object Register : AppRoutes("register")
    object InstructorCourses : AppRoutes("instructor/courses")
    object StudentDashboard : AppRoutes("student/dashboard")
}