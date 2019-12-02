package me.cmulugeta.airlinesbook.di

import dagger.Binds
import dagger.Module
import me.cmulugeta.airlinesbook.data.store.remote.AirportsRemote
import me.cmulugeta.airlinesbook.data.store.remote.AirportsRemoteDataStore

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Dagger module to provide remote dependencies.
 */
@Module
abstract class RemoteModule {
    @Binds
    abstract fun bindRemoteStore(remote: AirportsRemoteDataStore): AirportsRemote
}