package ch.magdenbt.a1ccaclient.presentations.scenario

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ch.magdenbt.a1ccaclient.InitApp
import ch.magdenbt.a1ccaclient.R
import ch.magdenbt.a1ccaclient.Repositories
import ch.magdenbt.a1ccaclient.databinding.FragmentScenarioDetailsBinding
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.model.scenarios.entities.ScenarioResult
import ch.magdenbt.a1ccaclient.utils.viewModelCreator
import javax.inject.Inject

class ScenarioDetailsFragment : Fragment(R.layout.fragment_scenario_details) {

    @Inject
    lateinit var scenariosRepository: ScenariosRepository
    private val viewModel by viewModelCreator { ScenarioDetailsViewModel(scenariosRepository) }
    private val args by navArgs<ScenarioDetailsFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as InitApp).appComponent.scenarioDetailsComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentScenarioDetailsBinding.inflate(inflater, container, false)
        binding.goBackButton.setOnClickListener { findNavController().popBackStack() }
        Log.d("scenariosRepository", scenariosRepository.toString())
        viewModel.loadScenario(args.scenarioID)
        viewModel.currentScenario.observe(viewLifecycleOwner){
            binding.dateTextView.text = it.date.toString()
            binding.scenarioTextView.text = it.name
            binding.resultIcon.setImageResource(getIconId(it.result))
            binding.resultDescriptionTextView.text = it.resultDescription
        }
        return binding.root
    }

    private fun getIconId(scenarioResult: ScenarioResult): Int {
        return when (scenarioResult) {
            ScenarioResult.Success -> R.drawable.ic_ok
            ScenarioResult.Started -> R.drawable.ic_started
            else -> R.drawable.ic_error
        }
    }


}