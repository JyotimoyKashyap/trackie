package com.jyotimoykashyap.trackie

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import com.jyotimoykashyap.trackie.models.TimeEntry
import kotlinx.coroutines.*
import kotlin.time.Clock
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class TimerViewModel {
    private val repository = TimeLogRepository()
    private var timerJob: Job? = null
    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    // --- Internal State ---
    private val _isRunning = mutableStateOf(false)
    private val _elapsedTime = mutableStateOf(0L) // in seconds
    private var startTime: Instant? = null
    // Add state for holding the log entries
    private val _logEntries = mutableStateOf<List<TimeEntry>>(emptyList())

    // --- Publicly Exposed State ---
    val isRunning: State<Boolean> = _isRunning
    val description = mutableStateOf("")
    val elapsedTimeFormatted: State<String> = derivedStateOf {
        formatTime(_elapsedTime.value)
    }
    // Expose the log entries to the UI
    val logEntries: State<List<TimeEntry>> = _logEntries

    init {
        // Load logs when the ViewModel is created
        loadLogEntries()
    }

    fun loadLogEntries() {
        // Load entries and reverse them to show the newest first
        _logEntries.value = repository.readAllEntries().reversed()
    }

    fun toggleTimer() {
        if (_isRunning.value) {
            stopAndSaveTimer()
        } else {
            startTimer()
        }
    }

    private fun startTimer() {
        _isRunning.value = true
        startTime = Clock.System.now()
        timerJob = viewModelScope.launch {
            while (isActive) {
                _elapsedTime.value = (Clock.System.now() - (startTime ?: Clock.System.now())).inWholeSeconds
                delay(1000)
            }
        }
    }

    private fun stopAndSaveTimer() {
        timerJob?.cancel()
        _isRunning.value = false

        val endTime = Clock.System.now()
        val finalStartTime = startTime
        if (finalStartTime != null && description.value.isNotBlank()) {
            val entry = TimeEntry(
                startTime = finalStartTime,
                endTime = endTime,
                durationInSeconds = _elapsedTime.value,
                description = description.value
            )
            repository.saveTimeEntry(entry)
            // Refresh the log list after saving a new entry
            loadLogEntries()
        }

        // Reset for the next session
        _elapsedTime.value = 0L
        description.value = ""
        startTime = null
    }

    private fun formatTime(seconds: Long): String {
        val duration = seconds.seconds
        return duration.toComponents { hours, minutes, secs, _ ->
            String.format("%02d:%02d:%02d", hours, minutes, secs)
        }
    }
}