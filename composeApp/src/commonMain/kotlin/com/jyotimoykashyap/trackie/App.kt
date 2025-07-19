package com.jyotimoykashyap.trackie

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jyotimoykashyap.trackie.ui.main.MainScreen
import com.jyotimoykashyap.trackie.ui.main.TimerViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import trackie.composeapp.generated.resources.Res
import trackie.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MainScreen(viewModel = TimerViewModel())
}