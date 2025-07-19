package com.jyotimoykashyap.trackie.data


import kotlinx.browser.window

private const val STORAGE_KEY = "timeTrackerEntries"

// The actual web implementation for reading from localStorage.
actual fun readJsonFromStorage(): String? {
    return window.localStorage.getItem(STORAGE_KEY)
}

// The actual web implementation for writing to localStorage.
actual fun writeJsonToStorage(jsonString: String) {
    window.localStorage.setItem(STORAGE_KEY, jsonString)
}