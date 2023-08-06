package ch.magdenbt.a1ccaclient.presentations.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel (private val scenariosRepository: ScenariosRepository) : ViewModel() {

    private val _scenarios = MutableLiveData<List<Scenario>>()
    val scenarios:LiveData<List<Scenario>> = _scenarios

    init {
        viewModelScope.launch {
            scenariosRepository.getScenarios().collect {
                _scenarios.value = it.toList()
            }
        }
    }

    fun updateScenarios() {
        viewModelScope.launch {
            scenariosRepository.updateScenarios()
        }
    }
}
