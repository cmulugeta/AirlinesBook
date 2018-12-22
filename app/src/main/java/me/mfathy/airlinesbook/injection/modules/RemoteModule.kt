package me.cmulugeta.airlinesbook.injection.modules

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.cmulugeta.airlinesbook.BuildConfig
import me.cmulugeta.airlinesbook.data.preference.PreferenceHelper
import me.cmulugeta.airlinesbook.data.store.AirportsDataStore
import me.cmulugeta.airlinesbook.data.store.remote.AirportsRemoteDataStore
import me.cmulugeta.airlinesbook.data.store.remote.service.AuthServiceApi
import me.cmulugeta.airlinesbook.data.store.remote.service.OAuthInterceptor
import me.cmulugeta.airlinesbook.data.store.remote.service.RemoteServiceApi
import me.cmulugeta.airlinesbook.data.store.remote.service.ServiceFactory
import me.cmulugeta.airlinesbook.data.store.remote.utils.NetworkUtils
import me.cmulugeta.airlinesbook.data.store.remote.utils.NetworkUtilsImpl

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Dagger module to provide remote dependencies.
 */
@Module
abstract class RemoteModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideRemoteService(oAuthInterceptor: OAuthInterceptor): RemoteServiceApi {
            return ServiceFactory.makeRemoteService(BuildConfig.DEBUG, oAuthInterceptor)
        }

        @Provides
        @JvmStatic
        fun provideAuthService(): AuthServiceApi {
            return ServiceFactory.makeAuthService(BuildConfig.DEBUG)
        }

        @Provides
        @JvmStatic
        fun provideOAuthInterceptor(preferenceHelper: PreferenceHelper): OAuthInterceptor {
            return OAuthInterceptor(preferenceHelper)
        }

        @Provides
        @JvmStatic
        fun providesNetworkUtils(app: Application): NetworkUtils {
            return NetworkUtilsImpl(app)
        }

    }

    @Binds
    abstract fun bindRemoteStore(cache: AirportsRemoteDataStore): AirportsDataStore

}