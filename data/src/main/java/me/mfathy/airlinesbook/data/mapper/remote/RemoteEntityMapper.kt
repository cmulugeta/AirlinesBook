package me.cmulugeta.airlinesbook.data.mapper.remote

import me.cmulugeta.airlinesbook.data.mapper.EntityMapper


/**
 * Mapper contract to convert and map data entities.
 */
interface RemoteEntityMapper<E, D> : me.cmulugeta.airlinesbook.data.mapper.EntityMapper<E, D> {
    override fun mapToEntity(domain: D): E
}