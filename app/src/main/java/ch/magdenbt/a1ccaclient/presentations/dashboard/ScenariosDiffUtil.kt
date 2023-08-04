package ch.magdenbt.a1ccaclient.presentations.dashboard

import androidx.recyclerview.widget.DiffUtil
import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario

class ScenariosDiffUtil : DiffUtil.ItemCallback<Scenario>() {
    override fun areItemsTheSame(oldItem: Scenario, newItem: Scenario): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Scenario, newItem: Scenario): Boolean {
        return oldItem == newItem
    }


}