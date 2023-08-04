package ch.magdenbt.a1ccaclient.model.scenarios

import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.model.scenarios.entities.ScenariosFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ScenariosRepository {

    fun getScenarios(): StateFlow<Set<Scenario>>

    fun getScenario(filter: ScenariosFilter): Flow<Scenario>

    fun updateScenarios()
}
