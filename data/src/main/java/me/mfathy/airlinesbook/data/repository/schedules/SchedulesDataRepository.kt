package me.cmulugeta.airlinesbook.data.repository.schedules

import io.reactivex.Flowable
import me.cmulugeta.airlinesbook.data.model.ScheduleEntity
import me.cmulugeta.airlinesbook.data.store.AirportsDataStoreFactory
import javax.inject.Inject

class SchedulesDataRepository @Inject constructor(
        private val factory: AirportsDataStoreFactory
) : SchedulesRepository {
    override fun getFlightSchedules(origin: String, destination: String, flightDate: String, limit: Int, offset: Int): Flowable<List<ScheduleEntity>> {
        return factory.getDataStore(false, true)
                .getFlightSchedules(origin, destination, flightDate, limit, offset)
    }

    /*override fun getFlightScheduleDetails(airportCodes: Array<String>,
                                          lang: String,
                                          limit: Int,
                                          offset: Int): Flowable<List<AirportEntity>> {
        return Flowable.fromIterable(airportCodes.toMutableList())
                .flatMap { airportCode -> getAirport(airportCode, lang, limit, offset).toFlowable() }
                .toList()
                .toFlowable()
    }*/
}