package ch.magdenbt.a1ccaclient.model.scenarios.localsource.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.model.scenarios.entities.ScenarioResult
import ch.magdenbt.a1ccaclient.model.scenarios.localsource.room.ScenarioDbEntity.Companion.TABLE_SCENARIOS
import java.util.Date

@Entity(
    tableName = TABLE_SCENARIOS
)
data class ScenarioDbEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "result") val result: ScenarioResult,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "result_description") val resultDescription: String
) {

    fun toScenario() = Scenario(
        id = id, date = date, result = result, resultDescription = resultDescription, name = name
    )

    companion object {
        const val TABLE_SCENARIOS = "scenarios"

        fun fromScenario(scenario: Scenario) = ScenarioDbEntity(
            id = scenario.id,
            name = scenario.name,
            result = scenario.result,
            date = scenario.date,
            resultDescription = scenario.resultDescription
        )
    }
}

