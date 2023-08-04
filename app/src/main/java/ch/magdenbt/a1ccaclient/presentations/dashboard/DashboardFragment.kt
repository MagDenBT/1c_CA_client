package ch.magdenbt.a1ccaclient.presentations.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ch.magdenbt.a1ccaclient.R
import ch.magdenbt.a1ccaclient.Repositories
import ch.magdenbt.a1ccaclient.databinding.FragmentDashboardBinding
import ch.magdenbt.a1ccaclient.utils.viewModelCreator

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel by viewModelCreator { DashboardViewModel(Repositories.scenariosRepository) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val scenariosAdapter = ScenariosAdapter(ScenariosDiffUtil()) { id -> showDetails(id) }
        scenariosAdapter.submitList(viewModel.scenarios.value)

        viewModel.scenarios.observe(viewLifecycleOwner) {
            scenariosAdapter.submitList(it)
            scenariosAdapter.notifyDataSetChanged()
        }

        binding.scenariosRV.adapter = scenariosAdapter
        return binding.root
    }

    private fun showDetails(scenarioID: String){
        val direction = DashboardFragmentDirections.actionDashboardFragmentToScenarioFragment(scenarioID)
        findNavController().navigate(direction)
    }
}
