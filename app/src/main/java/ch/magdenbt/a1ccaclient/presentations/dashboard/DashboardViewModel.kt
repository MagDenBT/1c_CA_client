package ch.magdenbt.a1ccaclient.presentations.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.utils.Resource
import kotlinx.coroutines.launch

class DashboardViewModel (private val scenariosRepository: ScenariosRepository) : ViewModel() {

    val scenarios:LiveData<Resource<List<Scenario>>> =  scenariosRepository.getScenarios().asLiveData()

    fun updateScenarios() {
        viewModelScope.launch {
            scenariosRepository.updateScenarios()
        }
    }
}
