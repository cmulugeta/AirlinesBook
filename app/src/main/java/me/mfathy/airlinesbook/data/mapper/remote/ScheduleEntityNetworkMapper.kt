package me.cmulugeta.airlinesbook.data.mapper.remote

import me.cmulugeta.airlinesbook.data.model.ScheduleEntity
import me.cmulugeta.airlinesbook.data.store.remote.model.Schedule
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * A helper class to map ScheduleEntity to/from Schedule
 */
open class ScheduleEntityNetworkMapper @Inject constructor(private val flightMapper: FlightEntityNetworkMapper) : RemoteEntityMapper<ScheduleEntity, Schedule> {

    override fun mapToEntity(domain: Schedule): ScheduleEntity {
        return ScheduleEntity(
                domain.totalJourney.duration,
                domain.flight?.map { flightMapper.mapToEntity(it) }?.toList()
        )
    }
}