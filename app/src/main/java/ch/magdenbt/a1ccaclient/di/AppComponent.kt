package ch.magdenbt.a1ccaclient.di

import android.content.Context
import ch.magdenbt.a1ccaclient.model.scenarios.DataSource
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.model.scenarios.fakesource.InMemoryDataSource
import ch.magdenbt.a1ccaclient.model.scenarios.fakesource.InMemoryScenariosRepository
import ch.magdenbt.a1ccaclient.model.settings.AppSettings
import ch.magdenbt.a1ccaclient.model.settings.AppSettingsDataStore
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class, GeneralModules::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context):AppComponent
    }

    fun dashboardComponent():DashboardComponent.Factory
    fun scenarioDetailsComponent(): ScenarioDetailsComponent.Factory
    fun profileComponent(): ProfileComponent.Factory
}

@Module(subcomponents = [DashboardComponent::class, ScenarioDetailsComponent::class, ProfileComponent::class])
class AppSubcomponents


@Module
abstract class GeneralModules{

    @Binds
    abstract fun bindDataSource(inMemoryDataSource: InMemoryDataSource): DataSource

    @Binds
    abstract fun bindScenariosRepository(inMemoryScenariosRepository: InMemoryScenariosRepository): ScenariosRepository

    @Binds
    abstract fun bindAppSettings(appSettingsDataStore: AppSettingsDataStore): AppSettings


}
