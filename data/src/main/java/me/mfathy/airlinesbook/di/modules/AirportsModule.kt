package me.cmulugeta.airlinesbook.di.modules

import androidx.annotation.NonNull
import dagger.Module
import dagger.Provides
import me.cmulugeta.airlinesbook.data.repository.airports.AirportsDataRepository
import me.cmulugeta.airlinesbook.data.repository.airports.AirportsRepository
import me.cmulugeta.airlinesbook.data.repository.auth.AuthDataRepository
import me.cmulugeta.airlinesbook.data.repository.auth.AuthRepository
import javax.inject.Singleton

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Dagger module to provide remote dependencies.
 */

@Module
class AirportsModule {
    @Provides
    @Singleton
    @NonNull
    fun providesAirportsRepository(repository: AirportsDataRepository): AirportsRepository = repository
}