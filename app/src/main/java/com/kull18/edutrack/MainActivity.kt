package com.kull18.edutrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.kull18.edutrack.core.di.AppContainer
import com.kull18.edutrack.core.theme.ui.EduTrackTheme
import com.kull18.edutrack.features.auth.di.LoginModule
import com.kull18.edutrack.features.courses_list_instructor.di.CourseModule
import com.kull18.edutrack.features.login.presentation.screens.LoginScreen
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

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            EduTrackTheme {
                AppNavGraph(
                    navController = navController,
                    loginFactory = loginModule.provideLoginViewModelFactory(),
                    registerFactory = registerModule.provideRegisterViewModelFactory(),
                    courseListFactory = courserInstructorModule.provideCourseListViewModelFactory(),
                )
            }
        }
    }
}
