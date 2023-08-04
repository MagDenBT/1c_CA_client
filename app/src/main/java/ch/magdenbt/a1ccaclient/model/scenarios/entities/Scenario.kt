package ch.magdenbt.a1ccaclient.model.scenarios.entities

import java.time.LocalDateTime

data class Scenario(
    val id: String,
    val date: LocalDateTime,
    var result: ScenarioResult,
    val name: String,
    val resultDescription: String,
)
