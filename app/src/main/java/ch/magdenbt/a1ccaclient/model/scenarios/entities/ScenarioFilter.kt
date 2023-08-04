package ch.magdenbt.a1ccaclient.model.scenarios.entities

class ScenariosFilter(
    private val scenarioResults: List<ScenarioResult> = emptyList(),
    private val id: String? = null
) {

    fun isMatch(scenario: Scenario): Boolean {
        var result = true

        if (scenarioResults.isNotEmpty()) {
            result = scenarioResults.contains(scenario.result)
        }

        if (id != null) {
            result = scenario.id == id
        }

        return result
    }
}
