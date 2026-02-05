package com.kull18.edutrack.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainScaffold(
    topAppBar: @Composable (() -> Unit)? = null,
    bottomAppBar: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = { topAppBar?.invoke() },
        bottomBar = { bottomAppBar?.invoke() }
    ) {paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
                .fillMaxSize()
                .background(Color.White),
        ) {
            content(paddingValues)
        }
    }
}