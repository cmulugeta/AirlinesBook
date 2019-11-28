package me.cmulugeta.airlinesbook.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableObserver
import io.reactivex.subscribers.DisposableSubscriber
import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import me.cmulugeta.airlinesbook.data.model.ScheduleEntity
import me.cmulugeta.airlinesbook.domain.interactor.schedules.GetFlightSchedules
import me.cmulugeta.airlinesbook.domain.interactor.token.GetAccessToken
import me.cmulugeta.airlinesbook.features.base.BaseViewModel
import me.cmulugeta.airlinesbook.features.state.Resource
import me.cmulugeta.airlinesbook.features.state.ResourceState
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 17/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Search view model which will handle getting access token and searching for flights.
 */
open class SearchViewModel @Inject internal constructor(
        private val getFlightSchedules: GetFlightSchedules
) : BaseViewModel() {
    private val schedulesLiveData: MutableLiveData<Resource<List<ScheduleEntity>>> = MutableLiveData()
    private val accessTokenLiveData: MutableLiveData<Resource<AccessTokenEntity>> = MutableLiveData()

    fun getSchedulesLiveData(): LiveData<Resource<List<ScheduleEntity>>> {
        return schedulesLiveData
    }

    fun getAccessTokenLiveData(): LiveData<Resource<AccessTokenEntity>> {
        return accessTokenLiveData
    }

    fun fetchFlightSchedules(origin: String, destination: String, flightDate: String, limit: Int, offset: Int) {
        schedulesLiveData.postValue(Resource(ResourceState.LOADING, null, null, null))
        val params = GetFlightSchedules.Params(
                origin,
                destination,
                flightDate,
                limit,
                offset
        )

        val schedulesSubscriber = getFlightSchedules.execute(params, FlightSchedulesSubscriber())
        addDisposables(schedulesSubscriber)
    }

    inner class FlightSchedulesSubscriber : DisposableSubscriber<List<ScheduleEntity>>() {
        override fun onComplete() {}

        override fun onNext(schedules: List<ScheduleEntity>?) {
            schedulesLiveData.postValue(Resource(ResourceState.SUCCESS, schedules, null, null))
        }

        override fun onError(e: Throwable) {
            schedulesLiveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage, e))
        }
    }


}