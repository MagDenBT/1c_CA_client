package ch.magdenbt.a1ccaclient.model.scenarios

import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import kotlinx.coroutines.flow.Flow
import ch.magdenbt.a1ccaclient.model.NoCachedData

interface DataSource {

    /**
     * @throws [NoCachedData]
     */
    fun getCachedData(): Flow<Scenario>

    fun getActualData(): List<Scenario>
}
