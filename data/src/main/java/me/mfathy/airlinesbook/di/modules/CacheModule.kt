package me.cmulugeta.airlinesbook.di.modules

import android.app.Application
import androidx.annotation.NonNull
import dagger.Module
import dagger.Provides
import me.cmulugeta.airlinesbook.data.store.local.AirportsCache
import me.cmulugeta.airlinesbook.data.store.local.AirportsCacheDataStore
import me.cmulugeta.airlinesbook.data.store.local.db.AirportsDatabase
import javax.inject.Singleton

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Dagger module to provide cache dependencies.
 */
@Module
class CacheModule {

    @Provides
    @Singleton
    @NonNull
    fun providesDatabase(application: Application): AirportsDatabase = AirportsDatabase.getInstance(application)

    @Provides
    @Singleton
    @NonNull
    fun providesCacheStore(cache: AirportsCacheDataStore): AirportsCache = cache
}