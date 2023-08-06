package ch.magdenbt.a1ccaclient.di

import ch.magdenbt.a1ccaclient.presentations.dashboard.DashboardFragment
import ch.magdenbt.a1ccaclient.presentations.dashboard.DashboardFragmentDirections
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface DashboardComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():DashboardComponent
    }
    fun inject(dashboardFragment: DashboardFragment)
}