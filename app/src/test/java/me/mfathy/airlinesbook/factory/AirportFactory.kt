package me.cmulugeta.airlinesbook.factory

import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.data.model.FlightEntity
import me.cmulugeta.airlinesbook.data.model.ScheduleEntity

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 */
object AirportFactory {
    fun makeAirportEntity(): AirportEntity {
        return AirportEntity(
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomLong().toDouble(),
                DataFactory.randomLong().toDouble(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString()
        )
    }

    fun makeScheduleEntity(): ScheduleEntity {
        return ScheduleEntity(
                DataFactory.randomString(),
                listOf(makeFlightEntity())
        )
    }

    private fun makeFlightEntity(): FlightEntity {
        return FlightEntity(
                Pair(first = DataFactory.randomString(), second = makeAirportEntity()),
                Pair(first = DataFactory.randomString(), second = makeAirportEntity()),
                DataFactory.randomInt(),
                DataFactory.randomString()
        )
    }
}