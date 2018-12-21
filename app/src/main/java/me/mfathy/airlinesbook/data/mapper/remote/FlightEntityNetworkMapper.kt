package me.cmulugeta.airlinesbook.data.mapper.remote

import me.cmulugeta.airlinesbook.data.mapper.EntityMapper
import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.data.model.FlightEntity
import me.cmulugeta.airlinesbook.data.store.remote.model.FlightItem
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 */
open class FlightEntityNetworkMapper @Inject constructor(): EntityMapper<FlightEntity, FlightItem> {
    override fun mapFromEntity(entity: FlightEntity): FlightItem {
        throw UnsupportedOperationException("FlightItem is readonly: parsed from remote data store only.")
    }

    override fun mapToEntity(domain: FlightItem): FlightEntity {
        return FlightEntity(
                Pair(
                        first = domain.departure.scheduledTimeLocal.dateTime,
                        second = AirportEntity(airportCode = domain.departure.airportCode)
                ),
                Pair(
                        first = domain.arrival.scheduledTimeLocal.dateTime,
                        second = AirportEntity(airportCode = domain.arrival.airportCode)
                ),
                domain.marketingCarrier.flightNumber,
                domain.marketingCarrier.airlineID
        )
    }

}