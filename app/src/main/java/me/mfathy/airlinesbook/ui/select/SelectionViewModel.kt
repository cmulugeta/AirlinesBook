package me.cmulugeta.airlinesbook.ui.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableObserver
import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.domain.interactor.airports.GetAirports
import me.cmulugeta.airlinesbook.ui.base.BaseViewModel
import me.cmulugeta.airlinesbook.ui.state.Resource
import me.cmulugeta.airlinesbook.ui.state.ResourceState
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 17/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * SelectionViewModel which handles get all airports.
 */
open class SelectionViewModel @Inject internal constructor(
        private val getAirports: GetAirports
) : BaseViewModel() {
    private val airportsLiveData: MutableLiveData<Resource<List<AirportEntity>>> = MutableLiveData()

    fun getAirportsLiveData(): LiveData<Resource<List<AirportEntity>>> {
        return airportsLiveData
    }

    fun fetchAirports(lang: String, limit: Int, offset: Int) {
        airportsLiveData.postValue(Resource(ResourceState.LOADING, null, null, null))
        val params = GetAirports.Params(
                lang,
                limit,
                offset
        )
        val airportsSubscriber = getAirports.execute(params, AirportsSubscriber())
        addDisposables(airportsSubscriber)

    }

    inner class AirportsSubscriber : DisposableObserver<List<AirportEntity>>() {
        override fun onComplete() {}

        override fun onNext(airports: List<AirportEntity>) {
            airportsLiveData.postValue(Resource(ResourceState.SUCCESS, airports, null, null))
        }

        override fun onError(e: Throwable) {
            airportsLiveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage, e))
        }
    }

}