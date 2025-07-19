package com.jyotimoykashyap.trackie.util

import java.io.File
import java.util.logging.FileHandler
import java.util.logging.Level
import java.util.logging.Logger
import java.util.logging.SimpleFormatter

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object Logger {

    private val LOGGER: Logger = Logger.getLogger("TrackieAppLogger")

    init {
        try {
            val homeDir = System.getProperty("user.home")
            val logDir = File(homeDir, ".TimeTrackerApp/logs")
            if (!logDir.exists()) logDir.mkdirs()

            val fileHandler = FileHandler("${logDir.path}/app.log", true)
            fileHandler.formatter = SimpleFormatter()
            LOGGER.addHandler(fileHandler)
            LOGGER.level = Level.ALL
            LOGGER.useParentHandlers = false // Optional: to avoid console output
        } catch (e: Exception) {
            println("FATAL: Could not initialize logger: ${e.message}")
        }
    }

    actual fun log(message: String) = LOGGER.info(message)

    actual fun warn(message: String, throwable: Throwable?) =
        LOGGER.log(Level.WARNING, message, throwable)

    actual fun error(message: String, throwable: Throwable?) =
        LOGGER.log(Level.SEVERE, message, throwable)

}