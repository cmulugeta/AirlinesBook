package me.cmulugeta.airlinesbook.injection

import dagger.Module
import dagger.Provides
import me.cmulugeta.airlinesbook.data.store.AirportsDataStore
import me.cmulugeta.airlinesbook.data.store.remote.service.RemoteServiceApi
import org.mockito.Mockito.mock

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideRemoteService(): RemoteServiceApi {
        return mock(RemoteServiceApi::class.java)
    }

    @Provides
    @JvmStatic
    fun provideRemoteDataStore(): AirportsDataStore {
        return mock(AirportsDataStore::class.java)
    }

}