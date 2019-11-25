package me.cmulugeta.airlinesbook.domain.interactor.airports

import io.reactivex.Observable
import me.cmulugeta.test.tools.ImmediateSchedulerRuleUnitTests
import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.data.repository.airports.AirportsRepository
import me.cmulugeta.airlinesbook.factory.AirportFactory
import me.cmulugeta.airlinesbook.factory.AirportFactory.makeGetAirportParams
import org.junit.Before
import org.junit.Rule
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

    @JvmField
    @Rule
    val immediateSchedulerRule = me.cmulugeta.test.tools.ImmediateSchedulerRuleUnitTests()

    private lateinit var mGetAirports: GetAirports

    @Mock
    lateinit var mockDataRepository: AirportsRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mGetAirports = GetAirports(mockDataRepository)
    }

    @Test
    fun testGetAirportsCompletes() {
        stubDataRepositoryGetAirports(
                Observable.just(listOf(
                        AirportFactory.makeAirportEntity(),
                        AirportFactory.makeAirportEntity()
                )))

        val params = makeGetAirportParams()

        mGetAirports.buildUseCaseObservable(params)
                .test()
                .assertComplete()
    }

    @Test
    fun testGetAirportsReturnsValue() {
        val airports = listOf(
                AirportFactory.makeAirportEntity(),
                AirportFactory.makeAirportEntity()
        )

        stubDataRepositoryGetAirports(
                Observable.just(airports))

        val params = makeGetAirportParams()

        mGetAirports.buildUseCaseObservable(params)
                .test()
                .assertValue(airports)
    }

    @Test
    fun testGetAirportsCallsRepository() {
        stubDataRepositoryGetAirports(
                Observable.just(listOf(
                        AirportFactory.makeAirportEntity(),
                        AirportFactory.makeAirportEntity()
                )))

        val params = makeGetAirportParams()

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
