package com.kull18.edutrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.kull18.edutrack.core.di.AppContainer
import com.kull18.edutrack.core.theme.ui.EduTrackTheme
import com.kull18.edutrack.presentation.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            EduTrackTheme {
                AppNavGraph(
                    navController = navController,
                )
            }
        }
    }
}
