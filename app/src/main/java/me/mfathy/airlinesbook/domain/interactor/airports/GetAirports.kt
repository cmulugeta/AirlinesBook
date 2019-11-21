package me.cmulugeta.airlinesbook.domain.interactor.airports

import io.reactivex.Observable
import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.data.repository.airports.AirportsRepository
import me.cmulugeta.airlinesbook.domain.interactor.base.ObservableUseCase
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * GetAirports use case to get all airports information from the data layer.
 */
open class GetAirports @Inject constructor(
        private val dataRepository: AirportsRepository)
    : ObservableUseCase<List<AirportEntity>, GetAirports.Params>() {
    override fun buildUseCaseObservable(params: Params): Observable<List<AirportEntity>> {
        return dataRepository.getAirports(params.lang, params.limit, params.offset)
    }

    data class Params constructor(val lang: String, val limit: Int, val offset: Int)
}