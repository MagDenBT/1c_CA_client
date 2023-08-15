package ch.magdenbt.a1ccaclient.model.scenarios

import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ScenariosRepository {

    fun getScenarios(): StateFlow<Resource<List<Scenario>>>

    suspend fun updateScenarios()
}
