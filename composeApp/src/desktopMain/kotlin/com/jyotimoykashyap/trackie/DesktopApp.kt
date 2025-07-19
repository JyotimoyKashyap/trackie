package com.jyotimoykashyap.trackie

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun DesktopApp(viewModel: TimerViewModel) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Timer", "Logbook")

    MaterialTheme(colors = darkColors()) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                TabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }

                // Content area that switches based on the selected tab
                when (selectedTab) {
                    0 -> TimerScreen(viewModel) // We'll extract the timer UI into this
                    1 -> LogbookScreen(viewModel.logEntries.value)
                }
            }
        }
    }
}


@Composable
@Preview
fun AppPreview() {
    DesktopApp(TimerViewModel())
}
