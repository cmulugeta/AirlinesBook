package me.cmulugeta.airlinesbook.data.mapper.memory

import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import me.cmulugeta.airlinesbook.data.store.memory.models.InMemoryToken

class InMemoryTokenMapper : MemoryEntityMapper<AccessTokenEntity, InMemoryToken> {
    override fun mapFromEntity(entity: AccessTokenEntity): InMemoryToken = InMemoryToken(
            expiresIn = entity.expiresIn,
            accessToken = entity.accessToken,
            clintId = entity.clintId,
            tokenType = entity.tokenType
    )

    override fun mapToEntity(domain: InMemoryToken): AccessTokenEntity = AccessTokenEntity(
            expiresIn = domain.expiresIn,
            accessToken = domain.accessToken,
            clintId = domain.clintId,
            tokenType = domain.tokenType
    )
}