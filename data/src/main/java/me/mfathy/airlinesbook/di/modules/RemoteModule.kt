package me.cmulugeta.airlinesbook.di.modules

import android.app.Application
import androidx.annotation.NonNull
import dagger.Module
import dagger.Provides
import me.cmulugeta.airlinesbook.data.BuildConfig
import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import me.cmulugeta.airlinesbook.data.store.remote.AirportsRemote
import me.cmulugeta.airlinesbook.data.store.remote.AirportsRemoteDataStore
import me.cmulugeta.airlinesbook.data.store.remote.model.RequestHeaders
import me.cmulugeta.airlinesbook.data.store.remote.service.AuthServiceApi
import me.cmulugeta.airlinesbook.data.store.remote.service.OAuthInterceptor
import me.cmulugeta.airlinesbook.data.store.remote.service.RemoteServiceApi
import me.cmulugeta.airlinesbook.data.store.remote.service.ServiceFactory
import me.cmulugeta.airlinesbook.data.store.remote.utils.NetworkUtils
import me.cmulugeta.airlinesbook.data.store.remote.utils.NetworkUtilsImpl
import javax.inject.Singleton

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Dagger module to provide remote dependencies.
 */

@Module
class RemoteModule {
    @Provides
    @Singleton
    @NonNull
    fun provideAuthService(): AuthServiceApi {
        return ServiceFactory.provideAuthService(BuildConfig.DEBUG)
    }

    @Provides
    @Singleton
    @NonNull
    fun provideRemoteService(oAuthInterceptor: OAuthInterceptor): RemoteServiceApi {
        return ServiceFactory.provideRemoteService(BuildConfig.DEBUG, oAuthInterceptor)
    }

    @Provides
    @Singleton
    @NonNull
    fun provideRequestHeaders(): RequestHeaders {
        val headers = RequestHeaders()
        headers.accessToken = AccessTokenEntity()
        return RequestHeaders()
    }

    @Provides
    @Singleton
    @NonNull
    fun provideOAuthInterceptor(@NonNull headers: RequestHeaders): OAuthInterceptor {
        return OAuthInterceptor(headers)
    }

    @Provides
    @Singleton
    @NonNull
    fun providesNetworkUtils(app: Application): NetworkUtils {
        return NetworkUtilsImpl(app)
    }

    @Provides
    @Singleton
    @NonNull
    fun providesRemoteStore(remote: AirportsRemoteDataStore): AirportsRemote {
        return remote
    }
}