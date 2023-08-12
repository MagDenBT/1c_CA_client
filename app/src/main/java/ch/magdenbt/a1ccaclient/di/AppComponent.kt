package ch.magdenbt.a1ccaclient.di

import android.content.Context
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepositoryImp
import ch.magdenbt.a1ccaclient.model.scenarios.localsource.ScenariosLocalDataSource
import ch.magdenbt.a1ccaclient.model.scenarios.localsource.room.RoomScenariosLocalDataSource
import ch.magdenbt.a1ccaclient.model.scenarios.remotesource.FakeScenariosRemoteDataSource
import ch.magdenbt.a1ccaclient.model.scenarios.remotesource.ScenariosRemoteDataSource
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

@Module(subcomponents = [DashboardComponent::class, ScenarioDetailsComponent::class, ProfileComponent::class], includes = [RoomModule::class])
class AppSubcomponents


@Module
abstract class GeneralModules{

    @Binds
    abstract fun bindScenariosLocalDataSource(roomScenariosLocalDataSource: RoomScenariosLocalDataSource): ScenariosLocalDataSource
    @Binds
    abstract fun bindFakeScenariosRemoteDataSource(fakeScenariosRemoteDataSource: FakeScenariosRemoteDataSource): ScenariosRemoteDataSource

    @Binds
    abstract fun bindScenariosRepository(scenariosRepositoryImp: ScenariosRepositoryImp): ScenariosRepository

    @Binds
    abstract fun bindAppSettings(appSettingsDataStore: AppSettingsDataStore): AppSettings


}
