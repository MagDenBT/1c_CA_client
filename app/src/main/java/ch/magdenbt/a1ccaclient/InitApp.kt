package ch.magdenbt.a1ccaclient

import android.app.Application
import android.content.Context
import ch.magdenbt.a1ccaclient.model.scenarios.DataSource
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.model.scenarios.fakesource.InMemoryDataSource
import ch.magdenbt.a1ccaclient.model.scenarios.fakesource.InMemoryScenariosRepository
import ch.magdenbt.a1ccaclient.model.settings.AppSettings
import ch.magdenbt.a1ccaclient.model.settings.AppSettingsDataStore

class InitApp : Application() {

    val dataSource: DataSource = InMemoryDataSource()
    val scenariosRepository: ScenariosRepository = InMemoryScenariosRepository(dataSource)
    val appSettings: AppSettings = AppSettingsDataStore(applicationContext)

}