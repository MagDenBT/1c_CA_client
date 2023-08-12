package ch.magdenbt.a1ccaclient.di

import android.content.Context
import androidx.room.Room
import ch.magdenbt.a1ccaclient.model.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideAppDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(context,AppDatabase::class.java, AppDatabase.DATABASE_FILE)
            .build()
    }

    @Singleton
    @Provides
    fun provideScenariosDao(appDatabase: AppDatabase) = appDatabase.getScenariosDao()
}



