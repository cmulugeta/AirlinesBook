package me.cmulugeta.airlinesbook.domain.interactor.airports

import io.reactivex.Observable
import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.data.repository.AirportsRepository
import me.cmulugeta.airlinesbook.domain.executor.ExecutionThread
import me.cmulugeta.airlinesbook.domain.executor.SubscribeThread
import me.cmulugeta.airlinesbook.domain.interactor.base.ObservableUseCase
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * GetAirports use case to get all airports information from the data layer.
 */
open class GetAirports @Inject constructor(
        private val dataRepository: AirportsRepository,
        val subscriberThread: SubscribeThread,
        val postExecutionThread: ExecutionThread)
    : ObservableUseCase<List<AirportEntity>, GetAirports.Params?>(subscriberThread, postExecutionThread) {
    public override fun buildUseCaseObservable(params: Params?): Observable<List<AirportEntity>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return dataRepository.getAirports(params.lang, params.limit, params.offset)
    }

    data class Params constructor(val lang: String, val limit: Int, val offset: Int) {
        companion object {
            fun forGetAirports(lang: String, limit: Int, offset: Int): Params {
                return Params(lang, limit, offset)
            }
        }
    }
}