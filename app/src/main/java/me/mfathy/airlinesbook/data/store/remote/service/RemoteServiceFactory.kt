package me.cmulugeta.airlinesbook.data.store.remote.service

import android.text.TextUtils
import com.google.gson.Gson
import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Mohammed Fathy on 11/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * RemoteServiceFactory is a factory class which is responsible for creating Okhttp client and creates
 * an implementation of retrofit remote service.
 */
object RemoteServiceFactory {

    fun makeRemoteService(isDebug: Boolean, baseUrl: String): RemoteService {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor((isDebug)))
        return makeRemoteService(okHttpClient, baseUrl, Gson(), null)
    }

    fun makeRemoteService(isDebug: Boolean, baseUrl: String, accessToken: AccessTokenEntity): RemoteService {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor((isDebug)))
        return makeRemoteService(okHttpClient, baseUrl, Gson(), accessToken)
    }

    private fun makeRemoteService(okHttpClient: OkHttpClient,
                                  baseUrl: String,
                                  gson: Gson,
                                  accessToken: AccessTokenEntity?): RemoteService {

        accessToken?.let {
            if(!TextUtils.isEmpty(it.accessToken)){
                val oAuthInterceptor = OAuthInterceptor(it)
                if (!okHttpClient.interceptors().contains(oAuthInterceptor)) {
                    okHttpClient.interceptors().add(oAuthInterceptor)
                }
            }
        }

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit.create(RemoteService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }


}