package me.cmulugeta.airlinesbook.data.mapper.memory

import me.cmulugeta.airlinesbook.data.mapper.EntityMapper


/**
 * Mapper contract to convert and map data entities.
 */
interface MemoryEntityMapper<E, D>: EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D

}