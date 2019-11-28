package me.cmulugeta.airlinesbook.injection.modules

import android.util.LruCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.cmulugeta.airlinesbook.data.mapper.memory.InMemoryTokenMapper
import me.cmulugeta.airlinesbook.data.store.memory.AuthMemoryStore
import me.cmulugeta.airlinesbook.data.store.memory.MemoryCache
import me.cmulugeta.airlinesbook.data.store.memory.models.InMemoryToken

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Dagger module to provide cache dependencies.
 */
@Module
abstract class MemoryModule {
    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesLruCache(): LruCache<String, InMemoryToken> {
            return LruCache(1)
        }

        @Provides
        @JvmStatic
        fun providesInMemoryTokenMapper(): InMemoryTokenMapper = InMemoryTokenMapper()
    }


    @Binds
    abstract fun bindMemoryStore(memoryCache: AuthMemoryStore): MemoryCache
}