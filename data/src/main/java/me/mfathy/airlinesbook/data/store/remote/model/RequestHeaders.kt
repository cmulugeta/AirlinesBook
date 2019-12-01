package me.cmulugeta.airlinesbook.data.store.remote.model

import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import javax.inject.Inject

class RequestHeaders @Inject constructor() {
    lateinit var accessToken: AccessTokenEntity
}