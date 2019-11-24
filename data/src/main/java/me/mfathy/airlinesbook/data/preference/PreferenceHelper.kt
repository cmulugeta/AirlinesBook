package me.cmulugeta.airlinesbook.data.preference

import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity

/**
 * Created by Mohammed Fathy on 22/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * PreferenceHelper Contract to read/write access token.
 */
interface PreferenceHelper {
    fun getAccessToken(): AccessTokenEntity
    fun setAccessToken(accessToken: AccessTokenEntity)
}