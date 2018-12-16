package me.cmulugeta.airlinesbook.data.store.remote.service

import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 */
class OAuthInterceptor(private val accessToken: AccessTokenEntity): Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
                .header("Accept","application/json")
                .header("Authorization", "${accessToken.tokenType.capitalize()} ${accessToken.accessToken}")

        val request = builder.build()
        return chain.proceed(request)
    }

}