package me.cmulugeta.airlinesbook.data.preference

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 19/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * A helper class manage read/write access token to a default shared preference.
 */
open class PreferenceHelperImpl @Inject constructor(val context: Context) : PreferenceHelper {

    private var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getAccessToken(): AccessTokenEntity {
        val tokenString = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "{}")
        return Gson().fromJson(tokenString, AccessTokenEntity::class.java)
    }

    override fun setAccessToken(accessToken: AccessTokenEntity) {
        val tokeString = Gson().toJson(accessToken)
        sharedPreferences.edit().putString(PREF_KEY_ACCESS_TOKEN, tokeString).apply()
    }

    companion object {
        const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    }
}