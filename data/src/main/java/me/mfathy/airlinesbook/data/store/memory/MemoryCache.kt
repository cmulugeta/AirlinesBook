package me.cmulugeta.airlinesbook.data.store.memory

import io.reactivex.Single
import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity

interface MemoryCache {
    fun get(key: String): Single<AccessTokenEntity>
    fun set(key: String, value: AccessTokenEntity)
}