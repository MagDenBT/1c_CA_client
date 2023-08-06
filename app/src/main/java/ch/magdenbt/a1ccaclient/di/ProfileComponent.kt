package ch.magdenbt.a1ccaclient.di

import ch.magdenbt.a1ccaclient.presentations.account.ProfileFragment
import dagger.Subcomponent

@Subcomponent
interface ProfileComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create() : ProfileComponent
    }

    fun inject( profileFragment: ProfileFragment)
}