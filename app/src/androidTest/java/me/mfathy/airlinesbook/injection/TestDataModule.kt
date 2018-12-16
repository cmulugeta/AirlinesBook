package me.cmulugeta.airlinesbook.injection

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import me.cmulugeta.airlinesbook.data.repository.AirportsRepository
import javax.inject.Singleton

@Module
object TestDataModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideDataRepository(): AirportsRepository {
        return mock()
    }

}