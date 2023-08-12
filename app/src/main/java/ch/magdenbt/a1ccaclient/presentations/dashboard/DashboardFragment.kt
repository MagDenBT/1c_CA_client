package ch.magdenbt.a1ccaclient.presentations.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ch.magdenbt.a1ccaclient.InitApp
import ch.magdenbt.a1ccaclient.R
import ch.magdenbt.a1ccaclient.databinding.FragmentDashboardBinding
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.utils.viewModelCreator
import javax.inject.Inject

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding
    @Inject
    lateinit var scenariosRepository: ScenariosRepository
    private val viewModel by viewModelCreator { DashboardViewModel(scenariosRepository) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as InitApp)
            .appComponent
            .dashboardComponent()
            .create()
            .inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val scenariosAdapter = ScenariosAdapter(ScenariosDiffUtil()) { id -> showDetails(id) }

        Log.d("scenariosRepository", scenariosRepository.toString())
        viewModel.scenarios.observe(viewLifecycleOwner) {result ->
            scenariosAdapter.submitList(result.data)
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
