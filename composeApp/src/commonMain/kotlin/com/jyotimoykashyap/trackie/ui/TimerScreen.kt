package com.jyotimoykashyap.trackie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jyotimoykashyap.trackie.ui.main.TimerViewModel

// Extract the original UI into its own Composable for cleanliness
@Composable
fun TimerScreen(viewModel: TimerViewModel) {
    val isRunning by viewModel.isRunning
    val elapsedTime by viewModel.elapsedTimeFormatted
    val description by viewModel.description

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Time display
        Text(
            text = elapsedTime,
            fontSize = 60.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(32.dp))

        // Description Text Field
        OutlinedTextField(
            value = description,
            onValueChange = { viewModel.description.value = it },
            label = { Text("What are you working on?") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isRunning
        )

        Spacer(Modifier.height(32.dp))

        // Start/Stop Button
        Button(
            onClick = { viewModel.toggleTimer() },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isRunning) Color(0xFFD32F2F) else Color(0xFF388E3C),
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            enabled = description.isNotBlank() || isRunning
        ) {
            Text(if (isRunning) "STOP" else "START", fontSize = 18.sp)
        }
    }
}