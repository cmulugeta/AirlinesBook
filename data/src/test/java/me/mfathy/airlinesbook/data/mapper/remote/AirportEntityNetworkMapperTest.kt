package me.cmulugeta.airlinesbook.data.mapper.remote

import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.data.store.remote.model.Airport
import me.cmulugeta.airlinesbook.factory.AirportFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Unit test for AirportEntityNetworkMapper
 */
@RunWith(JUnit4::class)
class AirportEntityNetworkMapperTest {

    private val mapper = AirportEntityNetworkMapper()

    @Test
    fun testMapToEntity() {
        val airport = AirportFactory.makeAirport()
        val entity = mapper.mapToEntity(airport)

        assertEqualsData(entity, airport)
    }


    private fun assertEqualsData(entity: AirportEntity, airport: Airport) {
        assertEquals(entity.name, airport.names.name.title)
        assertEquals(entity.locationType, airport.locationType)
        assertEquals(entity.longitude, airport.position.coordinate.longitude, 0.1)
        assertEquals(entity.latitude, airport.position.coordinate.latitude, 0.1)
        assertEquals(entity.countryCode, airport.countryCode)
        assertEquals(entity.cityCode, airport.cityCode)
        assertEquals(entity.airportCode, airport.airportCode)
    }
}