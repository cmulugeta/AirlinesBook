package me.cmulugeta.airlinesbook.injection.modules

import dagger.Binds
import dagger.Module
import me.cmulugeta.airlinesbook.data.repository.airports.AirportsDataRepository
import me.cmulugeta.airlinesbook.data.repository.airports.AirportsRepository
import me.cmulugeta.airlinesbook.data.repository.auth.AuthDataRepository
import me.cmulugeta.airlinesbook.data.repository.auth.AuthRepository
import me.cmulugeta.airlinesbook.data.repository.schedules.SchedulesDataRepository
import me.cmulugeta.airlinesbook.data.repository.schedules.SchedulesRepository
import javax.inject.Singleton

/**
 * Dagger module to provide data repository dependencies.
 */

@Module
abstract class DataModule {

    @Binds
    abstract fun bindAirportsRepository(repository: AirportsDataRepository): AirportsRepository

    @Binds
    abstract fun bindSchedulesRepository(repository: SchedulesDataRepository): SchedulesRepository

}