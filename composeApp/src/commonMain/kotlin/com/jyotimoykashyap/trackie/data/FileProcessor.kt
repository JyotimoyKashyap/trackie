package com.jyotimoykashyap.trackie.data

expect fun readJsonFromStorage(): String?

expect fun writeJsonToStorage(jsonString: String)