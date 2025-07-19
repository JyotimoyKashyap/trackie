package com.jyotimoykashyap.trackie.ui

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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jyotimoykashyap.trackie.models.TimeEntry
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
private fun formatInstant(instant: Instant): String {
    // 1. Convert kotlin.time.Instant to kotlinx.datetime.Instant
    val kotlinxInstant = Instant.fromEpochSeconds(instant.epochSeconds)

    // 2. Get the current system's timezone
    val systemTimeZone = TimeZone.currentSystemDefault()

    // 3. Convert the Instant to a local date and time in that timezone
    val localDateTime = kotlinxInstant.toLocalDateTime(systemTimeZone)

    // 4. Manually build the formatted string
    val dayOfWeek = localDateTime.dayOfWeek.name.take(3).lowercase().replaceFirstChar { it.titlecase() }
    val dayOfMonth = localDateTime.day.toString().padStart(2, '0')
    val month = localDateTime.month.name.take(3).lowercase().replaceFirstChar { it.titlecase() }
    val year = localDateTime.year
    val hour = localDateTime.hour.toString().padStart(2, '0')
    val minute = localDateTime.minute.toString().padStart(2, '0')

    return "$dayOfWeek, $dayOfMonth $month $year at $hour:$minute"
}

// A new Composable for displaying a single log entry
@OptIn(ExperimentalTime::class)
@Composable
fun LogEntryItem(entry: TimeEntry) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
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