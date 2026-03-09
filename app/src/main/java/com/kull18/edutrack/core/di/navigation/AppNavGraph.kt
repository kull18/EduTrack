package com.kull18.edutrack.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.app.ui.screens.CreateCourseScreen
import com.kull18.edutrack.features.course_delete.presentation.screens.DeleteCourseScreen
import com.kull18.edutrack.features.course_detail.presentation.screens.CourseDetailScreen
import com.kull18.edutrack.features.course_edit.presentation.screens.EditCourseScreen
import com.kull18.edutrack.features.courses_list.presentation.screens.CoursesScreen
import com.kull18.edutrack.features.login.presentation.screens.LoginScreen
import com.kull18.edutrack.features.register.presentation.screens.RegisterScreen
import java.net.URLDecoder

@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Login.route
    ) {
        composable(AppRoutes.Login.route) {
            LoginScreen(
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
                onEditClick = { courseId ->
                    navController.navigate(AppRoutes.EditCourse.createRoute(courseId))
                },
                onViewClick = { courseId ->
                    navController.navigate(AppRoutes.CourseDetail.createRoute(courseId))
                },
                onDeleteClick = { courseId, courseName ->
                    navController.navigate(AppRoutes.DeleteCourse.createRoute(courseId, courseName))
                },
                onCreateCourseClick = {
                    navController.navigate(AppRoutes.CreateCourse.route)
                }
            )
        }

        composable(
            route = AppRoutes.DeleteCourse.route,
            arguments = listOf(
                navArgument("courseId") {
                    type = NavType.IntType
                },
                navArgument("courseName") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            val encodedName = backStackEntry.arguments?.getString("courseName") ?: ""
            val courseName = URLDecoder.decode(encodedName, "UTF-8")
            DeleteCourseScreen(
                courseId = courseId,
                courseName = courseName,
                onBackClick = {
                    navController.popBackStack()
                },
                onDeleteSuccess = {
                    navController.navigate(AppRoutes.InstructorCourses.route) {
                        popUpTo(AppRoutes.InstructorCourses.route) { inclusive = true }
                    }
                }
            )
        }


        composable(AppRoutes.CreateCourse.route) {
            CreateCourseScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSuccess = {
                    navController.navigate(AppRoutes.InstructorCourses.route) {
                        popUpTo(AppRoutes.InstructorCourses.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = AppRoutes.CourseDetail.route,
            arguments = listOf(
                navArgument("courseId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            CourseDetailScreen(
                courseId = courseId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = AppRoutes.EditCourse.route,
            arguments = listOf(
                navArgument("courseId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            EditCourseScreen(
                courseId = courseId,
                onBackClick = {
                    navController.popBackStack()
                },
                onSuccess = {
                    navController.navigate(AppRoutes.InstructorCourses.route) {
                        popUpTo(AppRoutes.InstructorCourses.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppRoutes.StudentDashboard.route) {
            Text("Dashboard de Alumno")
        }
    }
}