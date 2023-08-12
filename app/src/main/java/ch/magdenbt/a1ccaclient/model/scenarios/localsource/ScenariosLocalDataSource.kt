package ch.magdenbt.a1ccaclient.model.scenarios.localsource

import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ScenariosLocalDataSource {

    fun fetchData(): Flow<List<Scenario>>

    suspend fun saveData(scenarios: List<Scenario>)

}