package com.kull18.edutrack.presentation.navigation

sealed class AppRoutes(val route: String) {

    // ─── Auth ──────────────────────────────────────────────
    object Login : AppRoutes("login")
    object Register : AppRoutes("register")

    // ─── Instructor: Cursos ────────────────────────────────
    object InstructorCourses : AppRoutes("instructor/courses")
    object CreateCourse : AppRoutes("courses/create")

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

    // ─── Instructor: Lecciones ─────────────────────────────
    object LessonList : AppRoutes("courses/{courseId}/lessons?courseName={courseName}") {
        fun createRoute(courseId: Int, courseName: String) =
            "courses/$courseId/lessons?courseName=${java.net.URLEncoder.encode(courseName, "UTF-8")}"
    }

    object CreateLesson : AppRoutes("courses/{courseId}/lessons/create") {
        fun createRoute(courseId: Int) = "courses/$courseId/lessons/create"
    }

    // leccionJson = Leccion serializada con Gson y URLEncodeada
    object EditLesson : AppRoutes("courses/{courseId}/lessons/edit/{leccionJson}") {
        fun createRoute(courseId: Int, leccionJson: String) =
            "courses/$courseId/lessons/edit/$leccionJson"
    }

    // ─── Alumno ────────────────────────────────────────────
    object StudentDashboard : AppRoutes("student/dashboard")
    object MyCourses : AppRoutes("student/my-courses")

    object CourseDetailStudent : AppRoutes("student/courses/detail/{courseId}") {
        fun createRoute(courseId: Int) = "student/courses/detail/$courseId"
    }

    object EnrollmentConfirmation : AppRoutes("student/courses/enrolled/{courseId}?courseName={courseName}&instructorName={instructorName}") {
        fun createRoute(courseId: Int, courseName: String, instructorName: String): String {
            val enc = java.net.URLEncoder.encode(courseName, "UTF-8")
            val encInstructor = java.net.URLEncoder.encode(instructorName, "UTF-8")
            return "student/courses/enrolled/$courseId?courseName=$enc&instructorName=$encInstructor"
        }
    }

    object StudentLessonList : AppRoutes("student/courses/{courseId}/lessons?courseName={courseName}") {
        fun createRoute(courseId: Int, courseName: String) =
            "student/courses/$courseId/lessons?courseName=${java.net.URLEncoder.encode(courseName, "UTF-8")}"
    }
}