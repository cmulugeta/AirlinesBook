package me.cmulugeta.airlinesbook.data.store.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import me.cmulugeta.airlinesbook.data.config.AppConstants
import me.cmulugeta.airlinesbook.data.store.local.models.CacheConfig

/**
 * A data access object class to read/write application config which is used
 * to manage caching expire policy.
 */
@Dao
abstract class CacheConfigDao {

    @Query(me.cmulugeta.airlinesbook.data.config.AppConstants.QUERY_GET_CONFIG)
    abstract fun getCacheConfig(): Single<CacheConfig>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCacheConfig(cacheConfig: CacheConfig): Single<Long>


}