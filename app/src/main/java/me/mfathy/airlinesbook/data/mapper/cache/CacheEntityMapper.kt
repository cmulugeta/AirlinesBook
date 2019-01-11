package me.cmulugeta.airlinesbook.data.mapper.cache

import me.cmulugeta.airlinesbook.data.mapper.EntityMapper


/**
 * Mapper contract to convert and map data entities.
 */
interface CacheEntityMapper<E, D>: EntityMapper<E, D>{

    fun mapFromEntity(entity: E): D

}