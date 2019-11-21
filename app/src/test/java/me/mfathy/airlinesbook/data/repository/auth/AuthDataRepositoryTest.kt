package me.cmulugeta.airlinesbook.data.repository.auth

import io.reactivex.Completable
import io.reactivex.Single
import me.cmulugeta.airlinesbook.BuildConfig
import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import me.cmulugeta.airlinesbook.data.preference.PreferenceHelper
import me.cmulugeta.airlinesbook.data.store.AirportsDataStore
import me.cmulugeta.airlinesbook.data.store.AirportsDataStoreFactory
import me.cmulugeta.airlinesbook.data.store.local.AirportsCache
import me.cmulugeta.airlinesbook.data.store.remote.AirportsRemote
import me.cmulugeta.airlinesbook.factory.AirportFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Mohammed Fathy on 17/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Unit test for AirportsDataRepository
 */
@RunWith(MockitoJUnitRunner::class)
class AuthDataRepositoryTest {

    private val mockCacheStore = mock(AirportsCache::class.java)
    private val mockRemoteStore = mock(AirportsRemote::class.java)
    private val mockFactory = mock(AirportsDataStoreFactory::class.java)
    private val mockPreferenceHelper = mock(PreferenceHelper::class.java)
    private val repository = AuthDataRepository(mockFactory, mockPreferenceHelper)

    @Before
    fun setup() {
        stubFactoryGetCachedDataStore()
    }

    @Test
    fun testGetAccessTokenCompletes() {
        val entity = AccessTokenEntity(BuildConfig.CLIENT_ID)
        stubPreferenceHelper(entity)
        stubGetAccessTokenEntity(Single.just(entity))
        stubSetLastCacheTime(Completable.complete())
        stubFactoryGetRemoteDataStore()
        val testObserver = repository.getAccessToken(
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET,
                BuildConfig.GRANT_TYPE).test()
        testObserver.assertComplete()
    }

    @Test
    fun testGetAccessTokenReturnsData() {
        val entity = AccessTokenEntity(BuildConfig.CLIENT_ID)
        stubPreferenceHelper(entity)
        stubGetAccessTokenEntity(Single.just(entity))
        stubSetLastCacheTime(Completable.complete())
        stubFactoryGetRemoteDataStore()
        val testObserver = repository.getAccessToken(
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET,
                BuildConfig.GRANT_TYPE).test()
        testObserver.assertValue(entity)
    }

    @Test
    fun testGetCachedAccessTokenReturnsData() {
        val entity = AirportFactory.makeAccessTokenEntity()
        stubPreferenceHelper(entity)
        val testObserver = repository.getAccessToken(
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET,
                BuildConfig.GRANT_TYPE).test()
        testObserver.assertValue(entity)
    }


    private fun stubPreferenceHelper(entity: AccessTokenEntity) {
        `when`(mockPreferenceHelper.getAccessToken()).thenReturn(entity)
    }

    private fun stubFactoryGetCachedDataStore() {
        `when`(mockFactory.getCacheDataStore()).thenReturn(mockCacheStore)
    }

    private fun stubFactoryGetRemoteDataStore() {
        `when`(mockFactory.getRemoteDataStore()).thenReturn(mockRemoteStore)
    }

    private fun stubSetLastCacheTime(completable: Completable) {
        `when`(mockCacheStore.setLastCacheTime(anyLong())).thenReturn(completable)
    }

    private fun stubGetAccessTokenEntity(observable: Single<AccessTokenEntity>) {
        `when`(mockRemoteStore.getAccessToken(anyString(), anyString(), anyString())).thenReturn(observable)
    }
}
