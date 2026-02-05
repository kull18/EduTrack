// presentation/navigation/AppNavGraph.kt
package com.kull18.edutrack.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kull18.edutrack.features.auth.presentation.viewmodels.LoginViewModelFactory
import com.kull18.edutrack.features.auth.presentation.viewmodels.RegisterViewModelFactory
import com.kull18.edutrack.features.courses_list_instructor.presentation.screens.CoursesScreen
import com.kull18.edutrack.features.courses_list_instructor.presentation.viewmodels.CourseListViewModelFactory
import com.kull18.edutrack.features.login.presentation.screens.LoginScreen
import com.kull18.edutrack.features.register.presentation.screens.RegisterScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    loginFactory: LoginViewModelFactory,
    registerFactory: RegisterViewModelFactory,
    courseListFactory: CourseListViewModelFactory
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Login.route
    ) {
        composable(AppRoutes.Login.route) {
            LoginScreen(
                factory = loginFactory,
                onNavigateToRegister = {
                    navController.navigate(AppRoutes.Register.route)
                },
                onLoginSuccess = { userRole ->
                    when (userRole) {
                        "instructor" -> {
                            navController.navigate(AppRoutes.InstructorCourses.route) {
                                popUpTo(AppRoutes.Login.route) { inclusive = true }
                            }
                        }
                        "alumno" -> {
                            navController.navigate(AppRoutes.StudentDashboard.route) {
                                popUpTo(AppRoutes.Login.route) { inclusive = true }
                            }
                        }
                    }
                }
            )
        }

        composable(AppRoutes.Register.route) {
            RegisterScreen(
                factory = registerFactory,
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onRegisterSuccess = {
                    navController.navigate(AppRoutes.Login.route) {
                        popUpTo(AppRoutes.Register.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoutes.InstructorCourses.route) {
            CoursesScreen(
                factory = courseListFactory,
                onEditClick = { courseId ->
                    // TODO: navController.navigate("courses/edit/$courseId")
                },
                onViewClick = { courseId ->
                    // TODO: navController.navigate("courses/detail/$courseId")
                },
                onDeleteClick = { courseId ->
                    // TODO: Manejar eliminación
                }
            )
        }

        composable(AppRoutes.StudentDashboard.route) {
            // TODO: Crear StudentDashboardScreen
            androidx.compose.material3.Text("Dashboard de Alumno")
        }
    }
}