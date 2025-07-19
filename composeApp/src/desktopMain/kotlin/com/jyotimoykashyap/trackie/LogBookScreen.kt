@file:OptIn(ExperimentalTime::class)

package com.jyotimoykashyap.trackie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jyotimoykashyap.trackie.models.TimeEntry
import java.time.format.DateTimeFormatter
import java.time.ZoneId
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

// Helper function to format the Instant to a readable date/time string
fun formatInstant(instant: Instant): String {
    val formatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy 'at' HH:mm")
        .withZone(ZoneId.systemDefault())
    return formatter.format(java.time.Instant.ofEpochSecond(instant.epochSeconds))
}

// A new Composable for displaying a single log entry
@Composable
fun LogEntryItem(entry: TimeEntry) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = entry.description,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Duration: ${entry.durationInSeconds.seconds}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Completed: ${formatInstant(entry.endTime)}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
        }
    }
}


// The new Logbook screen Composable
@Composable
fun LogbookScreen(logs: List<TimeEntry>) {
    if (logs.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No time entries logged yet.", style = MaterialTheme.typography.headlineMedium)
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(logs) { entry ->
                LogEntryItem(entry)
            }
        }
    }
}