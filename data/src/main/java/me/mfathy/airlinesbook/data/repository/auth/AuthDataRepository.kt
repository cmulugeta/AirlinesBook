package me.cmulugeta.airlinesbook.data.repository.auth

import io.reactivex.Single
import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import me.cmulugeta.airlinesbook.data.preference.PreferenceHelper
import me.cmulugeta.airlinesbook.data.store.AirportsDataStoreFactory
import javax.inject.Inject

class AuthDataRepository @Inject constructor(
        private val factory: AirportsDataStoreFactory,
        private val preferenceHelper: PreferenceHelper
): AuthRepository {
    override fun getAccessToken(clientId: String, clientSecret: String, grantType: String): Single<AccessTokenEntity> {

        //  Get cached access token.
        val accessToken = preferenceHelper.getAccessToken()
        return if (accessToken.accessToken.isBlank())
            //  Get access token from remote API.
            factory.getRemoteDataStore()
                    .getAccessToken(
                            clientId,
                            clientSecret,
                            grantType
                    ).flatMap {
                        //  Save cache expire trigger.
                        factory.getCacheDataStore().setLastCacheTime(it.expiresIn)
                                .andThen(Single.just(it))
                    }.doOnSuccess {
                        //  Save access token after getting it from Remote Api.
                        preferenceHelper.setAccessToken(it)
                    }
        else
            Single.just(accessToken)
    }
}