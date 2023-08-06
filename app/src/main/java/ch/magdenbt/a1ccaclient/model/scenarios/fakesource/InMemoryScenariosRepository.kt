package ch.magdenbt.a1ccaclient.model.scenarios.fakesource

import ch.magdenbt.a1ccaclient.model.NoCachedData
import ch.magdenbt.a1ccaclient.model.scenarios.DataSource
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.model.scenarios.entities.ScenariosFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryScenariosRepository @Inject constructor(private val dataSource: DataSource) : ScenariosRepository {

    private val _cache = MutableStateFlow<Set<Scenario>>(emptySet())


    override fun getScenarios(): StateFlow<Set<Scenario>> {
        if (_cache.value.isEmpty()) updateCache()
        return _cache
    }

    override fun getScenario(filter: ScenariosFilter): Flow<Scenario> {
        return _cache.value.filter { scenario -> filter.isMatch(scenario) }.asFlow()
    }

    private fun updateCache() {
        _cache.value = dataSource.getActualData().toSet()
    }

    override fun updateScenarios() {
        _cache.value = dataSource.getActualData().toSet()
    }
}
