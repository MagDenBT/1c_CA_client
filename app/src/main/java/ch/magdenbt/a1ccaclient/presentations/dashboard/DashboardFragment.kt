package ch.magdenbt.a1ccaclient.presentations.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ch.magdenbt.a1ccaclient.InitApp
import ch.magdenbt.a1ccaclient.R
import ch.magdenbt.a1ccaclient.databinding.FragmentDashboardBinding
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.utils.getQueryTextChangeStateFlow
import ch.magdenbt.a1ccaclient.utils.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var scenariosRepository: ScenariosRepository
    private val viewModel by viewModelCreator { DashboardViewModel(scenariosRepository) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as InitApp).appComponent.dashboardComponent().create().inject(this)

    }


    @OptIn(FlowPreview::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val scenariosAdapter = ScenariosAdapter(ScenariosDiffUtil()) { id -> showDetails(id) }

        Log.d("scenariosRepository", scenariosRepository.toString())
        viewModel.filteredScenarios.observe(viewLifecycleOwner) { result ->
            scenariosAdapter.submitList(result.data)
            scenariosAdapter.notifyDataSetChanged()
        }
        binding.scenariosRV.adapter = scenariosAdapter

        prepareSearchMenu()
        return binding.root

    }


    private fun prepareSearchMenu() {
        activity?.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)
                val menuItem = menu.findItem(R.id.search_bar)
                val query =
                    (menuItem.actionView as SearchView).getQueryTextChangeStateFlow().debounce(600)
                        .distinctUntilChanged().flowOn(Dispatchers.Default)
                viewModel.setSearchQuery(query)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showDetails(scenarioID: String) {
        val direction =
            DashboardFragmentDirections.actionDashboardFragmentToScenarioFragment(scenarioID)
        findNavController().navigate(direction)
    }
}
