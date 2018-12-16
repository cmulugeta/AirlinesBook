package me.cmulugeta.airlinesbook.data.mapper.cache

import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.data.store.local.models.CachedAirport
import me.cmulugeta.airlinesbook.factory.AirportFactory
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 */
@RunWith(JUnit4::class)
class AirportEntityCacheMapperTest{

    private val mapper = AirportEntityCacheMapper()

    @Test
    fun testMapFromEntityMapsData(){
        val entity = AirportFactory.makeAirportEntity()
        val cachedAirport = mapper.mapFromEntity(entity)

        assertEqualData(entity, cachedAirport)
    }

    @Test
    fun testMapTOEntityMapsData(){
        val cachedAirport = AirportFactory.makeCachedAirport()
        val entity = mapper.mapToEntity(cachedAirport)

        assertEqualData(entity, cachedAirport)
    }

    private fun assertEqualData(entity: AirportEntity, domain: CachedAirport) {
        assertEquals(entity.airportCode, domain.airportCode)
        assertEquals(entity.cityCode, domain.cityCode)
        assertEquals(entity.countryCode, domain.countryCode)
        assertEquals(entity.latitude, domain.latitude, 0.1)
        assertEquals(entity.longitude, domain.longitude, 0.1)
        assertEquals(entity.locationType, domain.locationType)
        assertEquals(entity.name, domain.name)
    }
}