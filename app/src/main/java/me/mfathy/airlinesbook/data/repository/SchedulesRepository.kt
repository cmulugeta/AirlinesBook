package me.cmulugeta.airlinesbook.data.repository

import io.reactivex.Flowable
import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.data.model.ScheduleEntity

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Data repository contact to define layer actions.
 */
interface SchedulesRepository {

    /**
     * Returns a flowable which emits a list of flight schedule entities.
     * @param origin airport code that the user will travel from
     * @param destination airport code that the user will travel to
     * @param limit the number of flight schedules.
     * @return a flowable which emits a list of flight schedule entities or error.
     */
    fun getFlightSchedules(origin: String,
                           destination: String,
                           flightDate: String,
                           limit: Int,
                           offset: Int): Flowable<List<ScheduleEntity>>
}