package com.jyotimoykashyap.trackie.repository

import com.jyotimoykashyap.trackie.models.TimeEntry

interface TimeLogRepository {
    fun saveTimeEntry(timeEntry: TimeEntry)
    fun readAllEntries(): List<TimeEntry>
}