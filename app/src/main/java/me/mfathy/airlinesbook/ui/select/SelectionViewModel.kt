package me.cmulugeta.airlinesbook.ui.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.domain.interactor.airports.GetAirports
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
) : ViewModel() {
    private val airportsLiveData: MutableLiveData<Resource<List<AirportEntity>>> = MutableLiveData()


    override fun onCleared() {
        getAirports.dispose()
        super.onCleared()
    }

    fun getAirportsLiveData(): LiveData<Resource<List<AirportEntity>>> {
        return airportsLiveData
    }

    fun fetchAirports(lang: String, limit: Int, offset: Int) {
        airportsLiveData.postValue(Resource(ResourceState.LOADING, null, null, null))
        getAirports.execute(
                AirportsSubscriber(),
                GetAirports.Params.forGetAirports(
                        lang,
                        limit,
                        offset
                )
        )

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