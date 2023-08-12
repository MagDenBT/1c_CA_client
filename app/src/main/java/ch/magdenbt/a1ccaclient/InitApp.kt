package ch.magdenbt.a1ccaclient

import android.app.Application
import ch.magdenbt.a1ccaclient.di.AppComponent
import ch.magdenbt.a1ccaclient.di.DaggerAppComponent

class InitApp : Application() {


    val appComponent by lazy {
        initializeAppComponent()
    }

    private fun initializeAppComponent():AppComponent =
        DaggerAppComponent.factory().create(applicationContext)


}