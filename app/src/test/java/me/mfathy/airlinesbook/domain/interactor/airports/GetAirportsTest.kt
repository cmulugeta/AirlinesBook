package me.cmulugeta.airlinesbook.domain.interactor.airports

import io.reactivex.Observable
import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.data.repository.AirportsRepository
import me.cmulugeta.airlinesbook.domain.executor.ExecutionThread
import me.cmulugeta.airlinesbook.domain.executor.SubscribeThread
import me.cmulugeta.airlinesbook.factory.AirportFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Unit test for GetAirports use case.
 */
@RunWith(MockitoJUnitRunner::class)
class GetAirportsTest {

    private lateinit var mGetAirports: GetAirports

    @Mock
    lateinit var mockDataRepository: AirportsRepository
    @Mock
    lateinit var mockExecutionThread: ExecutionThread
    @Mock
    lateinit var mockSubscribeThread: SubscribeThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mGetAirports = GetAirports(mockDataRepository, mockSubscribeThread, mockExecutionThread)
    }

    @Test
    fun testGetAirportsCompletes() {
        stubDataRepositoryGetAirports(
                Observable.just(listOf(
                        AirportFactory.makeAirportEntity(),
                        AirportFactory.makeAirportEntity()
                )))

        val params = GetAirports.Params.forGetAirports("en", 2, 1)
        val testObserver = mGetAirports.buildUseCaseObservable(params).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun testGetAirportsThrowsException() {
        mGetAirports.buildUseCaseObservable().test()
    }

    @Test
    fun testGetAirportsCallsRepository() {
        stubDataRepositoryGetAirports(
                Observable.just(listOf(
                        AirportFactory.makeAirportEntity(),
                        AirportFactory.makeAirportEntity()
                )))

        val params = GetAirports.Params.forGetAirports("en", 2, 1)
        mGetAirports.buildUseCaseObservable(params).test()
        verify(mockDataRepository).getAirports(anyString(), anyInt(), anyInt())
    }

    private fun stubDataRepositoryGetAirports(observable: Observable<List<AirportEntity>>) {
        `when`(mockDataRepository.getAirports(
                anyString(),
                anyInt(),
                anyInt()
        )).thenReturn(observable)
    }
}
