package ch.magdenbt.a1ccaclient.model.scenarios

import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ScenariosRepository {

    fun getScenarios(): Flow<Resource<List<Scenario>>>

    suspend fun updateScenarios()
}
