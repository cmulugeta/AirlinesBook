package me.cmulugeta.airlinesbook.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.subscribers.DisposableSubscriber
import me.cmulugeta.test.tools.ImmediateSchedulerRuleUnitTests
import me.cmulugeta.test.tools.any
import me.cmulugeta.test.tools.argumentCaptor
import me.cmulugeta.test.tools.capture
import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.domain.interactor.schedules.GetScheduleFlightDetails
import me.cmulugeta.airlinesbook.factory.AirportFactory
import me.cmulugeta.airlinesbook.factory.DataFactory
import me.cmulugeta.airlinesbook.ui.state.ResourceState
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by Mohammed Fathy on 22/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Unit tests for FlightDetailsViewModel
 */
@RunWith(JUnit4::class)
class FlightDetailsViewModelTest {

    @JvmField
    @Rule
    val immediateSchedulerRule = me.cmulugeta.test.tools.ImmediateSchedulerRuleUnitTests()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    private var mockGetScheduleFlightDetails = mock(GetScheduleFlightDetails::class.java)

    private var flightDetailsViewModel = FlightDetailsViewModel(mockGetScheduleFlightDetails)

    @Captor
    val airportListCaptor = me.cmulugeta.test.tools.argumentCaptor<DisposableSubscriber<List<AirportEntity>>>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testFetchAirportsExecutesUseCase() {
        stubGetFlightDetailsUseCase()

        flightDetailsViewModel.fetchAirports(listOf(), "en", 1, 1)

        verify(mockGetScheduleFlightDetails, times(1)).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.any())
    }

    @Test
    fun testFetchAirportsReturnsSuccess() {

        stubGetFlightDetailsUseCase()

        val airports = listOf(AirportFactory.makeAirportEntity())

        flightDetailsViewModel.fetchAirports(listOf(), "", 1, 1)

        verify(mockGetScheduleFlightDetails).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(airportListCaptor))
        airportListCaptor.value.onNext(airports)

        assertEquals(ResourceState.SUCCESS, flightDetailsViewModel.getAirportsLiveData().value?.status)
    }

    @Test
    fun testFetchAirportsReturnsData() {

        stubGetFlightDetailsUseCase()

        val airports = listOf(AirportFactory.makeAirportEntity())

        flightDetailsViewModel.fetchAirports(listOf(), "", 1, 1)

        verify(mockGetScheduleFlightDetails).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(airportListCaptor))

        airportListCaptor.value.onNext(airports)

        assertEquals(airports, flightDetailsViewModel.getAirportsLiveData().value?.data)
    }

    @Test
    fun testFetchAirportsReturnsError() {

        stubGetFlightDetailsUseCase()

        flightDetailsViewModel.fetchAirports(listOf(), "", 1, 1)

        verify(mockGetScheduleFlightDetails).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(airportListCaptor))

        airportListCaptor.value.onError(RuntimeException())

        assertEquals(ResourceState.ERROR, flightDetailsViewModel.getAirportsLiveData().value?.status)
    }

    @Test
    fun testFetchAirportsReturnsMessageForError() {

        stubGetFlightDetailsUseCase()

        val errorMessage = DataFactory.randomString()
        flightDetailsViewModel.fetchAirports(listOf(), "", 1, 1)

        verify(mockGetScheduleFlightDetails).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(airportListCaptor))

        airportListCaptor.value.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage, flightDetailsViewModel.getAirportsLiveData().value?.message)
    }

    private fun stubGetFlightDetailsUseCase() {
        `when`(mockGetScheduleFlightDetails.execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.any())).thenReturn(flightDetailsViewModel.AirportsSubscriber())
    }
}
