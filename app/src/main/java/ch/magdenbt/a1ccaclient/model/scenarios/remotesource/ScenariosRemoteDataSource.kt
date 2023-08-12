package ch.magdenbt.a1ccaclient.model.scenarios.remotesource

import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import kotlinx.coroutines.flow.Flow

interface ScenariosRemoteDataSource {
    fun fetchData() : List<Scenario>
}