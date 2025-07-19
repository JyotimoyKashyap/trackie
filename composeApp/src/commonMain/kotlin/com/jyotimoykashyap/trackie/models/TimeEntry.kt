package com.jyotimoykashyap.trackie.models


import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


@Serializable
data class TimeEntry @OptIn(ExperimentalTime::class) constructor(
    @Serializable(with = InstantSerializer::class)
    val startTime: Instant,
    @Serializable(with = InstantSerializer::class)
    val endTime: Instant,
    val durationInSeconds: Long,
    val description: String
)
