package me.cmulugeta.airlinesbook.domain.interactor.schedules

import io.reactivex.Flowable
import me.cmulugeta.airlinesbook.data.model.ScheduleEntity
import me.cmulugeta.airlinesbook.data.repository.schedules.SchedulesRepository
import me.cmulugeta.airlinesbook.domain.interactor.base.FlowableUseCase
import me.cmulugeta.airlinesbook.extensions.rx.subscribeAndObserve
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * GetFlightSchedules use case to get all flight schedule information from the data layer.
 */

open class GetFlightSchedules @Inject constructor(
        private val dataRepository: SchedulesRepository)
    : FlowableUseCase<List<ScheduleEntity>, GetFlightSchedules.Params>() {
    override fun buildUseCaseObservable(params: Params): Flowable<List<ScheduleEntity>> {
        return dataRepository.getFlightSchedules(params.origin,
                params.destination,
                params.flightDate,
                params.limit,
                params.offset).subscribeAndObserve()
    }

    data class Params constructor(val origin: String,
                                  val destination: String,
                                  val flightDate: String,
                                  val limit: Int,
                                  val offset: Int)
}
