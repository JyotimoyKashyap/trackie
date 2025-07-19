package com.jyotimoykashyap.trackie

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.jyotimoykashyap.trackie.ui.main.TimerViewModel

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "trackie",
    ) {
        App()
    }
}