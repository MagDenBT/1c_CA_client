package ch.magdenbt.a1ccaclient.model.scenarios

import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.model.scenarios.localsource.ScenariosLocalDataSource
import ch.magdenbt.a1ccaclient.model.scenarios.remotesource.ScenariosRemoteDataSource
import ch.magdenbt.a1ccaclient.utils.Resource
import ch.magdenbt.a1ccaclient.utils.networkBoundResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScenariosRepositoryImp @Inject constructor(
    private val localDataSource: ScenariosLocalDataSource,
    private val remoteDataSource: ScenariosRemoteDataSource,
    ioDispatcher: CoroutineDispatcher
) : ScenariosRepository {

    private val _scenarios : MutableStateFlow<Resource<List<Scenario>>> = MutableStateFlow(Resource.Loading<List<Scenario>>(
        emptyList()
    ))


    init {
        CoroutineScope(ioDispatcher).launch {
            networkBoundResource(
                query = { localDataSource.fetchData() },
                fetch = {
                    delay(2000)
                    remoteDataSource.fetchData()
                },
                saveFetchResult = { listScenarios -> localDataSource.saveData(listScenarios) }
            ).collect{_scenarios.value = it }
        }
    }

   override fun getScenarios(): Flow<Resource<List<Scenario>>> {
      return _scenarios
   }

    override suspend fun updateScenarios() {
        localDataSource.saveData(remoteDataSource.fetchData())
    }

}
