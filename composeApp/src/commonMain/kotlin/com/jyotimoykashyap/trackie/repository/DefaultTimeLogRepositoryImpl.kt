package com.jyotimoykashyap.trackie.repository

import com.jyotimoykashyap.trackie.data.readJsonFromStorage
import com.jyotimoykashyap.trackie.data.writeJsonToStorage
import com.jyotimoykashyap.trackie.models.TimeEntry
import com.jyotimoykashyap.trackie.util.Logger
import kotlinx.serialization.json.Json

class DefaultTimeLogRepositoryImpl : TimeLogRepository {

    private val json = Json { prettyPrint = true }

    override fun saveTimeEntry(timeEntry: TimeEntry) {
        val existingEntries = readAllEntries().toMutableList()
        existingEntries.add(timeEntry)
        val jsonString = json.encodeToString(existingEntries)
        // call platform-specific logic to save write the text
        writeJsonToStorage(jsonString)
    }

    override fun readAllEntries(): List<TimeEntry> =
        readJsonFromStorage()?.takeIf { it.isNotBlank() }?.let {
            try {
                json.decodeFromString(it)
            } catch (ex: Exception) {
                Logger.error("Failed to parse json from storage: $it")
                emptyList()
            }
        } ?: emptyList()
}