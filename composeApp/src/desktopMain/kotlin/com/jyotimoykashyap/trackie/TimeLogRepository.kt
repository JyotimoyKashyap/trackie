package com.jyotimoykashyap.trackie

import com.jyotimoykashyap.trackie.models.TimeEntry
import kotlinx.serialization.json.Json
import java.io.File

class TimeLogRepository {
    private val logFile: File

    // A nice, pretty-printed JSON format
    private val json = Json { prettyPrint = true }

    init {
        // Store the log file in a hidden directory within the user's home directory
        val homeDir = System.getProperty("user.home")
        val appDir = File(homeDir, ".TimeTrackerApp")
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        logFile = File(appDir, "timelogs.json")
    }

    fun saveTimeEntry(entry: TimeEntry) {
        // Read existing entries, add the new one, and write back to the file
        val existingEntries = readAllEntries().toMutableList()
        existingEntries.add(entry)
        val jsonString = json.encodeToString(existingEntries)
        logFile.writeText(jsonString)
    }

    fun readAllEntries(): List<TimeEntry> {
        return if (logFile.exists() && logFile.readText().isNotBlank()) {
            try {
                json.decodeFromString(logFile.readText())
            } catch (e: Exception) {
                // Handle potential file corruption or format errors
                println("Error reading log file: ${e.message}")
                emptyList()
            }
        } else {
            emptyList()
        }
    }
}