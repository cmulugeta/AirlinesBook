package me.cmulugeta.airlinesbook.domain.schedules

import io.reactivex.Flowable
import me.cmulugeta.airlinesbook.data.model.ScheduleEntity
import me.cmulugeta.airlinesbook.data.repository.AirportsRepository
import me.cmulugeta.airlinesbook.domain.executor.ExecutionThread
import me.cmulugeta.airlinesbook.domain.interactor.FlowableUseCase
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * GetFlightSchedules use case to get all flight schedule information from the data layer.
 */

open class GetFlightSchedules @Inject constructor(
        private val dataRepository: AirportsRepository,
        subscriberThread: ExecutionThread,
        postExecutionThread: ExecutionThread)
    : FlowableUseCase<List<ScheduleEntity>, GetFlightSchedules.Params?>(subscriberThread, postExecutionThread) {
    public override fun buildUseCaseObservable(params: GetFlightSchedules.Params?): Flowable<List<ScheduleEntity>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return dataRepository.getFlightSchedules(params.origin,
                params.destination,
                params.limit,
                params.offset)
    }

    data class Params constructor(val origin: String,
                                  val destination: String,
                                  val limit: Int,
                                  val offset: Int) {
        companion object {
            fun forGetFlightSchedules(origin: String, destination: String, limit: Int, offset: Int): Params {
                return Params(origin, destination, limit, offset)
            }
        }
    }
}
