package me.cmulugeta.airlinesbook.di.modules

import android.util.LruCache
import androidx.annotation.NonNull
import dagger.Module
import dagger.Provides
import me.cmulugeta.airlinesbook.data.mapper.memory.InMemoryTokenMapper
import me.cmulugeta.airlinesbook.data.store.memory.AuthMemoryStore
import me.cmulugeta.airlinesbook.data.store.memory.MemoryCache
import me.cmulugeta.airlinesbook.data.store.memory.models.InMemoryToken
import javax.inject.Singleton

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Dagger module to provide cache dependencies.
 */
@Module
class MemoryModule {

    @Provides
    @Singleton
    @NonNull
    fun providesLruCache(): LruCache<String, InMemoryToken> = LruCache(1)

    @Provides
    @Singleton
    @NonNull
    fun providesInMemoryTokenMapper(): InMemoryTokenMapper = InMemoryTokenMapper()

    @Provides
    @Singleton
    @NonNull
    fun providesMemoryStore(memoryCache: AuthMemoryStore): MemoryCache = memoryCache

}