package com.jyotimoykashyap.trackie.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jyotimoykashyap.trackie.ui.LogbookScreen
import com.jyotimoykashyap.trackie.ui.TimerScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(viewModel: TimerViewModel) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Timer", "Logbook")

    MaterialTheme {
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
    MainScreen(TimerViewModel())
}