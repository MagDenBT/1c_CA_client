package ch.magdenbt.a1ccaclient.model.scenarios.entities

import java.time.LocalDateTime
import java.util.Date

data class Scenario(
    val id: String,
    val date: Date,
    var result: ScenarioResult,
    val name: String,
    val resultDescription: String,
)
