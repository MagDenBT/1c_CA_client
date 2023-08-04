package ch.magdenbt.a1ccaclient.presentations.scenario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.model.scenarios.entities.ScenariosFilter

import kotlinx.coroutines.launch

class ScenarioDetailsViewModel(private val scenariosRepository: ScenariosRepository) : ViewModel() {

    private val _currentScenario = MutableLiveData<Scenario>(null)
    val currentScenario: LiveData<Scenario> = _currentScenario

    fun loadScenario(scenarioId: String) {
        viewModelScope.launch {
            scenariosRepository.getScenario(ScenariosFilter(id = scenarioId))
                .collect { _currentScenario.value = it }
        }
    }
}
