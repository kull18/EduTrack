package com.kull18.edutrack.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.app.ui.screens.CreateCourseScreen
import com.google.gson.Gson
import com.kull18.edutrack.features.course_delete.presentation.screens.DeleteCourseScreen
import com.kull18.edutrack.features.course_detail.presentation.screens.CourseDetailScreen
import com.kull18.edutrack.features.course_edit.presentation.screens.EditCourseScreen
import com.kull18.edutrack.features.course_registration.presentation.screens.CourseRegistrationScreen
import com.kull18.edutrack.features.course_registration.presentation.screens.StudentLessonListScreen
import com.kull18.edutrack.features.courses_list.presentation.screens.CoursesScreen
import com.kull18.edutrack.features.lesson.domain.entities.Leccion
import com.kull18.edutrack.features.lesson.presentation.screens.CreateLessonScreen
import com.kull18.edutrack.features.lesson.presentation.screens.EditLessonScreen
import com.kull18.edutrack.features.lesson.presentation.screens.LessonListScreen
import com.kull18.edutrack.features.login.presentation.screens.LoginScreen
import com.kull18.edutrack.features.register.presentation.screens.RegisterScreen
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
    val gson = Gson()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.Login.route
    ) {

        // ─── AUTH ──────────────────────────────────────────
        composable(AppRoutes.Login.route) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(AppRoutes.Register.route)
                },
                onLoginSuccess = { userRole ->
                    when (userRole) {
                        "instructor" -> navController.navigate(AppRoutes.InstructorCourses.route) {
                            popUpTo(AppRoutes.Login.route) { inclusive = true }
                        }
                        "alumno" -> navController.navigate(AppRoutes.StudentDashboard.route) {
                            popUpTo(AppRoutes.Login.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(AppRoutes.Register.route) {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate(AppRoutes.Login.route) {
                        popUpTo(AppRoutes.Register.route) { inclusive = true }
                    }
                }
            )
        }

        // ─── INSTRUCTOR: CURSOS ────────────────────────────
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
                },
                onLessonsClick = { courseId, courseName ->
                    navController.navigate(AppRoutes.LessonList.createRoute(courseId, courseName))
                }
            )
        }

        composable(AppRoutes.CreateCourse.route) {
            CreateCourseScreen(
                onBackClick = { navController.popBackStack() },
                onSuccess = {
                    navController.navigate(AppRoutes.InstructorCourses.route) {
                        popUpTo(AppRoutes.InstructorCourses.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = AppRoutes.CourseDetail.route,
            arguments = listOf(navArgument("courseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            CourseDetailScreen(
                courseId = courseId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = AppRoutes.EditCourse.route,
            arguments = listOf(navArgument("courseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            EditCourseScreen(
                courseId = courseId,
                onBackClick = { navController.popBackStack() },
                onSuccess = {
                    navController.navigate(AppRoutes.InstructorCourses.route) {
                        popUpTo(AppRoutes.InstructorCourses.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = AppRoutes.DeleteCourse.route,
            arguments = listOf(
                navArgument("courseId") { type = NavType.IntType },
                navArgument("courseName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            val encodedName = backStackEntry.arguments?.getString("courseName") ?: ""
            val courseName = URLDecoder.decode(encodedName, "UTF-8")
            DeleteCourseScreen(
                courseId = courseId,
                courseName = courseName,
                onBackClick = { navController.popBackStack() },
                onDeleteSuccess = {
                    navController.navigate(AppRoutes.InstructorCourses.route) {
                        popUpTo(AppRoutes.InstructorCourses.route) { inclusive = true }
                    }
                }
            )
        }

        // ─── INSTRUCTOR: LECCIONES ─────────────────────────

        composable(
            route = AppRoutes.LessonList.route,
            arguments = listOf(
                navArgument("courseId") { type = NavType.IntType },
                navArgument("courseName") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            val encodedName = backStackEntry.arguments?.getString("courseName") ?: ""
            val courseName = URLDecoder.decode(encodedName, "UTF-8")
            LessonListScreen(
                cursoId = courseId,
                courseName = courseName,
                onBackClick = { navController.popBackStack() },
                onAddLessonClick = {
                    navController.navigate(AppRoutes.CreateLesson.createRoute(courseId))
                },
                onEditLesson = { leccion ->
                    // Serializar Leccion a JSON → URLEncode → pasar como String en la ruta
                    val leccionJson = URLEncoder.encode(gson.toJson(leccion), "UTF-8")
                    navController.navigate(AppRoutes.EditLesson.createRoute(courseId, leccionJson))
                }
            )
        }

        composable(
            route = AppRoutes.CreateLesson.route,
            arguments = listOf(
                navArgument("courseId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            CreateLessonScreen(
                cursoId = courseId,
                onBackClick = { navController.popBackStack() },
                onSuccess = {
                    navController.popBackStack() // Volver a LessonList
                }
            )
        }

        composable(
            route = AppRoutes.EditLesson.route,
            arguments = listOf(
                navArgument("courseId") { type = NavType.IntType },
                navArgument("leccionJson") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            val encodedLeccion = backStackEntry.arguments?.getString("leccionJson") ?: ""
            // URLDecode → deserializar JSON → objeto Leccion
            val leccion = gson.fromJson(
                URLDecoder.decode(encodedLeccion, "UTF-8"),
                Leccion::class.java
            )
            EditLessonScreen(
                cursoId = courseId,
                leccion = leccion,
                onBackClick = { navController.popBackStack() },
                onSuccess = { navController.popBackStack() }
            )
        }

        // ─── ALUMNO ────────────────────────────────────────
        composable(AppRoutes.StudentDashboard.route) {
            CourseRegistrationScreen(
                onCourseClick = { courseId, courseName ->
                    navController.navigate(AppRoutes.StudentLessonList.createRoute(courseId, courseName))
                }
            )
        }

        composable(
            route = AppRoutes.StudentLessonList.route,
            arguments = listOf(
                navArgument("courseId") { type = NavType.IntType },
                navArgument("courseName") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            val encodedName = backStackEntry.arguments?.getString("courseName") ?: ""
            val courseName = URLDecoder.decode(encodedName, "UTF-8")
            StudentLessonListScreen(
                cursoId = courseId,
                courseName = courseName,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}