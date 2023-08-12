package ch.magdenbt.a1ccaclient.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class UtilsModule {
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}