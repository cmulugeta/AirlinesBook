package me.cmulugeta.airlinesbook.injection

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import me.cmulugeta.airlinesbook.data.store.AirportsDataStore
import me.cmulugeta.airlinesbook.data.store.remote.service.RemoteServiceApi

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideRemoteService(): RemoteServiceApi {
        return mock()
    }

    @Provides
    @JvmStatic
    fun provideRemoteDataStore(): AirportsDataStore {
        return mock()
    }

}