package ch.magdenbt.a1ccaclient.presentations.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.magdenbt.a1ccaclient.R
import ch.magdenbt.a1ccaclient.databinding.ScenarioItemBinding
import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.model.scenarios.entities.ScenarioResult

class ScenariosAdapter(itemCallback: DiffUtil.ItemCallback<Scenario>, val onClickListener : (String)-> Unit) :
    ListAdapter<Scenario, ScenarioViewHolder>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScenarioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ScenarioItemBinding.inflate(inflater, parent, false)
        return ScenarioViewHolder(itemBinding, onClickListener)
    }

    override fun onBindViewHolder(holder: ScenarioViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ScenarioViewHolder(val itemBinding: ScenarioItemBinding, val onClickListener : (String)-> Unit ) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(scenario: Scenario) {
        itemBinding.dateTextView.text = scenario.date.toString()
        itemBinding.scenarioTextView.text = scenario.name
        itemBinding.resultDescriptionTextView.text = scenario.resultDescription
        itemBinding.resultIcon.setImageResource(getIconId(scenario.result))
        itemBinding.containerLayout.setOnClickListener {onClickListener(scenario.id)}
    }

    private fun getIconId(scenarioResult: ScenarioResult): Int {
        return when (scenarioResult) {
            ScenarioResult.Success -> R.drawable.ic_ok
            ScenarioResult.Started -> R.drawable.ic_started
            else -> R.drawable.ic_error
        }
    }
}
