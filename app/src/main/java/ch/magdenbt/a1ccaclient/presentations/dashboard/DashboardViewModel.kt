package ch.magdenbt.a1ccaclient.presentations.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class DashboardViewModel(private val scenariosRepository: ScenariosRepository) : ViewModel() {

    private val searchQuery = MutableStateFlow("")
    private val allScenarios: StateFlow<Resource<List<Scenario>>> =
        scenariosRepository.getScenarios()

    val filteredScenarios: LiveData<Resource<List<Scenario>>> = combine(
        allScenarios, searchQuery
    ) { scenariosResource, query ->
        filterScenarios(scenariosResource, query)
    }.asLiveData()

    fun updateScenarios() {
        viewModelScope.launch {
            scenariosRepository.updateScenarios()
        }
    }

    fun setSearchQuery(query: Flow<String>) {
        viewModelScope.launch {
            query.collect {
                searchQuery.value = it
            }
        }
    }

    private fun filterScenarios(
        scenariosResource: Resource<List<Scenario>>, query: String
    ): Resource<List<Scenario>> {
        val filteredScenariosResource = if (query.isBlank()) {
            scenariosResource
        } else {
            scenariosResource.copyWithFilteredData {
                scenariosFilter(
                    scenariosResource.data, query
                )
            }
        }
        return filteredScenariosResource
    }

    private fun scenariosFilter(scenariosList: List<Scenario>?, text: String): List<Scenario> {
        return scenariosList?.filter { scenarioHasText(it, text) } ?: emptyList()
    }

    private fun scenarioHasText(scenario: Scenario, text: String): Boolean {
        return scenario.date.toString().contains(text, ignoreCase = true) || scenario.name.contains(
            text, ignoreCase = true
        ) || scenario.resultDescription.contains(text, ignoreCase = true)
    }
}
