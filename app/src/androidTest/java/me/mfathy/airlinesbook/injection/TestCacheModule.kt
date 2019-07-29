package me.cmulugeta.airlinesbook.injection

import android.app.Application
import dagger.Module
import dagger.Provides
import me.cmulugeta.airlinesbook.data.store.AirportsDataStore
import me.cmulugeta.airlinesbook.data.store.local.db.AirportsDatabase
import org.mockito.Mockito.mock

@Module
object TestCacheModule {

    @Provides
    @JvmStatic
    fun provideDatabase(application: Application): AirportsDatabase {
        return AirportsDatabase.getInstance(application)
    }

    @Provides
    @JvmStatic
    fun provideCacheStore(): AirportsDataStore {
        return mock(AirportsDataStore::class.java)
    }

}