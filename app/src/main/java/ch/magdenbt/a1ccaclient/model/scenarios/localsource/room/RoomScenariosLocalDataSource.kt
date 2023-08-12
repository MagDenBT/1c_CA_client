package ch.magdenbt.a1ccaclient.model.scenarios.localsource.room

import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.model.scenarios.localsource.ScenariosLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomScenariosLocalDataSource @Inject constructor(private val scenariosDao: ScenariosDao) :
    ScenariosLocalDataSource {

    override fun fetchData(): Flow<List<Scenario>> {
        return scenariosDao.getDistinctScenarios().map { listDb -> listDb.map { it.toScenario() } }
    }

    override suspend fun saveData(scenarios: List<Scenario>) {
        scenariosDao.deleteAllScenarios()
        scenariosDao.saveScenarios(scenarios.map { ScenarioDbEntity.fromScenario(it) })
    }
}