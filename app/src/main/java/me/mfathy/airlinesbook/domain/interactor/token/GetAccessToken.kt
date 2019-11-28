package me.cmulugeta.airlinesbook.domain.interactor.token

import io.reactivex.Single
import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import me.cmulugeta.airlinesbook.data.repository.auth.AuthRepository
import me.cmulugeta.airlinesbook.domain.interactor.base.SingleUseCase
import me.cmulugeta.airlinesbook.extensions.rx.computation
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 18/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * GetAccessToken is a use case to get app access token.
 */
open class GetAccessToken @Inject constructor(
        private val authRepository: AuthRepository
) : SingleUseCase<AccessTokenEntity, GetAccessToken.Params>() {
    override fun buildUseCaseObservable(params: Params): Single<AccessTokenEntity> {
        return authRepository.getAccessToken(
                params.clientId,
                params.clientSecret,
                params.grantType
        )

    }

    data class Params constructor(val clientId: String, val clientSecret: String, val grantType: String)
}