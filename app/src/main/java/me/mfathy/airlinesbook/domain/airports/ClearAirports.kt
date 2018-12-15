package me.cmulugeta.airlinesbook.domain.airports

import io.reactivex.Completable
import me.cmulugeta.airlinesbook.data.repository.AirportsRepository
import me.cmulugeta.airlinesbook.domain.executor.ExecutionThread
import me.cmulugeta.airlinesbook.domain.interactor.CompletableUseCase
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * ClearAirports use case used for clearing data stores from airports.
 */
open class ClearAirports @Inject constructor(
        private val dataRepository: AirportsRepository,
        subscriberThread: ExecutionThread,
        postExecutionThread: ExecutionThread)
    : CompletableUseCase<ClearAirports.Params>(subscriberThread, postExecutionThread) {
    public override fun buildUseCaseCompletable(params: Params?): Completable {
        return dataRepository.clearAirports()
    }

    class Params

}