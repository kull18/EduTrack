package com.kull18.edutrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.kull18.edutrack.core.di.AppContainer
import com.kull18.edutrack.core.theme.ui.EduTrackTheme
import com.kull18.edutrack.features.auth.di.LoginModule
import com.kull18.edutrack.features.course_create.di.CreateCourseModule
import com.kull18.edutrack.features.course_delete.di.CourseDeleteModule
import com.kull18.edutrack.features.course_detail.di.CourseDetailModule
import com.kull18.edutrack.features.course_edit.di.CourseEditModule
import com.kull18.edutrack.features.courses_list.di.CourseModule
import com.kull18.edutrack.features.register.di.RegisterModule
import com.kull18.edutrack.presentation.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    lateinit var appContainer: AppContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = AppContainer(this)
        val loginModule = LoginModule(appContainer)
        val registerModule = RegisterModule(appContainer)
        val courserInstructorModule = CourseModule(appContainer)
        val creteCourseInstructorModule = CreateCourseModule(appContainer)
        val courseDetailInstructorModule = CourseDetailModule(appContainer)
        val courseEditInstructorModule = CourseEditModule(appContainer)
        val courseDeleteModule = CourseDeleteModule(appContainer)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            EduTrackTheme {
                AppNavGraph(
                    navController = navController,
                    loginFactory = loginModule.provideLoginViewModelFactory(),
                    registerFactory = registerModule.provideRegisterViewModelFactory(),
                    courseListFactory = courserInstructorModule.provideCourseListViewModelFactory(),
                    createCourseFactory = creteCourseInstructorModule.provideCreateCourseViewModelFactory(),
                    courseDetailFactory = courseDetailInstructorModule.provideCourseDetailViewModelFactory(),
                    editCourseFactory = courseEditInstructorModule.provideEditCourseViewModelFactory(),
                    deleteFactory = courseDeleteModule.provideDeleteCourseViewModelFactory()
                )
            }
        }
    }
}
