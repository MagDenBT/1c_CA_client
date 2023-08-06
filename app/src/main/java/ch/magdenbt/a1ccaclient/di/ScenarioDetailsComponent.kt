package ch.magdenbt.a1ccaclient.di

import ch.magdenbt.a1ccaclient.presentations.scenario.ScenarioDetailsFragment
import dagger.Subcomponent

@Subcomponent
interface ScenarioDetailsComponent {

    @Subcomponent.Factory
    interface Factory{
       fun create():ScenarioDetailsComponent
    }

    fun inject(scenarioDetailsFragment: ScenarioDetailsFragment)
}