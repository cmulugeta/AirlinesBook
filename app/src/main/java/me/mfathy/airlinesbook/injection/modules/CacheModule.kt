package me.cmulugeta.airlinesbook.injection.modules

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.cmulugeta.airlinesbook.data.store.AirportsDataStore
import me.cmulugeta.airlinesbook.data.store.local.AirportsCacheDataStore
import me.cmulugeta.airlinesbook.data.store.local.db.AirportsDatabase

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Dagger module to provide cache dependencies.
 */
@Module
abstract class CacheModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): AirportsDatabase {
            return AirportsDatabase.getInstance(application)
        }
    }


    @Binds
    abstract fun bindCacheStore(cache: AirportsCacheDataStore): AirportsDataStore
}