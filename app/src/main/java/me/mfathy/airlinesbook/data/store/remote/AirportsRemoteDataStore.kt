package me.cmulugeta.airlinesbook.data.store.remote

import dagger.Lazy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import me.cmulugeta.airlinesbook.data.mapper.remote.AccessTokenEntityNetworkMapper
import me.cmulugeta.airlinesbook.data.mapper.remote.AirportEntityNetworkMapper
import me.cmulugeta.airlinesbook.data.mapper.remote.ScheduleEntityNetworkMapper
import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.data.model.ScheduleEntity
import me.cmulugeta.airlinesbook.data.store.AirportsDataStore
import me.cmulugeta.airlinesbook.data.store.remote.service.AuthServiceApi
import me.cmulugeta.airlinesbook.data.store.remote.service.RemoteServiceApi
import me.cmulugeta.airlinesbook.data.store.remote.utils.NetworkUtils
import me.cmulugeta.airlinesbook.exception.NetworkConnectionException
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * AirportsRemoteDataStore is an implementation of AirportsDataStore to handle read remote API operations.
 */
open class AirportsRemoteDataStore @Inject constructor(
        private val networkUtils: NetworkUtils,
        private val authServiceApi: AuthServiceApi,
        private val serviceApi: Lazy<RemoteServiceApi>,
        private val accessDataMapper: AccessTokenEntityNetworkMapper,
        private val airportsDataMapper: AirportEntityNetworkMapper,
        private val scheduleDataMapper: ScheduleEntityNetworkMapper
) : AirportsRemote {

    override fun getAccessToken(clientId: String, clientSecret: String, grantType: String): Single<AccessTokenEntity> {
        return if (!networkUtils.hasConnection()) Single.error(NetworkConnectionException())
        else authServiceApi.getAccessToken(clientId, clientSecret, grantType).map {
            val accessToken = it.copy(clientId = clientId)
            accessDataMapper.mapToEntity(accessToken)
        }
    }

    override fun getAirports(lang: String, limit: Int, offset: Int): Flowable<List<AirportEntity>> {
        return if (!networkUtils.hasConnection()) Flowable.error(NetworkConnectionException())
        else serviceApi.get().getAirports(lang, limit, offset).map { airportsResponse ->
            airportsResponse.airportResource.airports.airportList.map {
                airportsDataMapper.mapToEntity(it)
            }
        }.toFlowable()
    }

    override fun getFlightSchedules(origin: String, destination: String, flightDate: String, limit: Int, offset: Int): Flowable<List<ScheduleEntity>> {
        return if (!networkUtils.hasConnection()) Flowable.error(NetworkConnectionException())
        else serviceApi.get().getFlightSchedules(origin, destination, flightDate, limit, offset).map { scheduleResponse ->
            scheduleResponse.scheduleResource.schedule.map {
                scheduleDataMapper.mapToEntity(it)
            }
        }.toFlowable()
    }

    override fun getAirport(airportCode: String, lang: String, limit: Int, offset: Int): Single<AirportEntity> {
        return if (!networkUtils.hasConnection()) Single.error(NetworkConnectionException())
        else serviceApi.get().getAirport(airportCode, lang, 1, offset).map { response ->
            airportsDataMapper.mapToEntity(response.airportResource.airports.airportList.first())
        }
    }
}