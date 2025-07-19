package com.jyotimoykashyap.trackie.data


import java.io.File


private const val LOG_FILE_DOESNT_EXIST = "Storage is inaccessible at this moment"

private val logFile: File by lazy {
    try {
        val homeDir = System.getProperty("user.home")
        val appDir = File(homeDir, ".TimeTrackerApp")
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        File(appDir, "timelogs.json")
    } catch (e: Exception) {
        throw e
    }
}

actual fun readJsonFromStorage(): String? {
    return if (logFile.exists()) logFile.readText() else null
}

actual fun writeJsonToStorage(jsonString: String) {
    if (logFile.exists()) logFile.writeText(jsonString) else throw Exception(LOG_FILE_DOESNT_EXIST)
}