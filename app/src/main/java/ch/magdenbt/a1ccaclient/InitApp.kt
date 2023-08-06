package ch.magdenbt.a1ccaclient

import android.app.Application
import android.content.Context
import ch.magdenbt.a1ccaclient.di.AppComponent
import ch.magdenbt.a1ccaclient.di.DaggerAppComponent
import ch.magdenbt.a1ccaclient.model.scenarios.DataSource
import ch.magdenbt.a1ccaclient.model.scenarios.ScenariosRepository
import ch.magdenbt.a1ccaclient.model.scenarios.fakesource.InMemoryDataSource
import ch.magdenbt.a1ccaclient.model.scenarios.fakesource.InMemoryScenariosRepository
import ch.magdenbt.a1ccaclient.model.settings.AppSettings
import ch.magdenbt.a1ccaclient.model.settings.AppSettingsDataStore

class InitApp : Application() {


    val appComponent by lazy {
        initializeAppComponent()
    }

    private fun initializeAppComponent():AppComponent =
        DaggerAppComponent.factory().create(applicationContext)


}