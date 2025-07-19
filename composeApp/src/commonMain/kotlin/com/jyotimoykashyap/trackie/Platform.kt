package com.jyotimoykashyap.trackie

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform