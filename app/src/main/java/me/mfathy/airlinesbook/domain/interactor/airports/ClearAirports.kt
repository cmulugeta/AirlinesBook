package me.cmulugeta.airlinesbook.domain.interactor.airports

import io.reactivex.Completable
import me.cmulugeta.airlinesbook.data.repository.airports.AirportsRepository
import me.cmulugeta.airlinesbook.domain.interactor.base.CompletableUseCase
import me.cmulugeta.airlinesbook.extensions.rx.subscribeAndObserve
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * ClearAirports use case used for clearing data stores from airports.
 */
open class ClearAirports @Inject constructor(
        private val dataRepository: AirportsRepository)
    : CompletableUseCase() {

    override fun buildUseCaseCompletable(): Completable {
        return dataRepository.clearAirports().subscribeAndObserve()
    }

    class Params
}