package me.cmulugeta.airlinesbook.ui.select

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.observers.DisposableObserver
import me.cmulugeta.airlinesbook.ImmediateSchedulerRuleUnitTests
import me.cmulugeta.airlinesbook.any
import me.cmulugeta.airlinesbook.argumentCaptor
import me.cmulugeta.airlinesbook.capture
import me.cmulugeta.airlinesbook.data.model.AirportEntity
import me.cmulugeta.airlinesbook.domain.interactor.airports.GetAirports
import me.cmulugeta.airlinesbook.factory.AirportFactory
import me.cmulugeta.airlinesbook.factory.DataFactory
import me.cmulugeta.airlinesbook.ui.state.ResourceState
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mockito.*

/**
 * Created by Mohammed Fathy on 22/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Unit test for SelectionViewModel
 */
@RunWith(JUnit4::class)
class SelectionViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val immediateSchedulerRule = ImmediateSchedulerRuleUnitTests()

    private val mockGetAirport = mock(GetAirports::class.java)
    private var selectionViewModel = SelectionViewModel(mockGetAirport)

    @Captor
    val airportListCaptor = argumentCaptor<DisposableObserver<List<AirportEntity>>>()

    @Test
    fun testFetchAirportsExecutesUseCase() {

        stubGetAirportsUseCase()

        selectionViewModel.fetchAirports(DataFactory.randomString(), DataFactory.randomInt(), DataFactory.randomInt())

        verify(mockGetAirport, times(1)).execute(any(), any())
    }

    @Test
    fun testFetchAirportsReturnsSuccess() {

        stubGetAirportsUseCase()

        val airports = listOf(AirportFactory.makeAirportEntity())

        selectionViewModel.fetchAirports(DataFactory.randomString(), DataFactory.randomInt(), DataFactory.randomInt())

        verify(mockGetAirport).execute(any(), capture(airportListCaptor))

        airportListCaptor.value.onNext(airports)

        Assert.assertEquals(ResourceState.SUCCESS, selectionViewModel.getAirportsLiveData().value?.status)
    }

    @Test
    fun testFetchAirportsReturnsData() {

        stubGetAirportsUseCase()

        val airports = listOf(AirportFactory.makeAirportEntity())

        selectionViewModel.fetchAirports(DataFactory.randomString(), DataFactory.randomInt(), DataFactory.randomInt())

        verify(mockGetAirport).execute(any(), capture(airportListCaptor))

        airportListCaptor.value.onNext(airports)


        Assert.assertEquals(airports, selectionViewModel.getAirportsLiveData().value?.data)
    }

    @Test
    fun testFetchAirportsReturnsError() {

        stubGetAirportsUseCase()

        selectionViewModel.fetchAirports(DataFactory.randomString(), DataFactory.randomInt(), DataFactory.randomInt())

        verify(mockGetAirport).execute(any(), capture(airportListCaptor))

        airportListCaptor.value.onError(RuntimeException())

        Assert.assertEquals(ResourceState.ERROR, selectionViewModel.getAirportsLiveData().value?.status)
    }

    @Test
    fun testFetchAirportsReturnsMessageForError() {

        stubGetAirportsUseCase()

        val errorMessage = DataFactory.randomString()
        selectionViewModel.fetchAirports(DataFactory.randomString(), DataFactory.randomInt(), DataFactory.randomInt())

        verify(mockGetAirport).execute(any(), capture(airportListCaptor))

        airportListCaptor.value.onError(RuntimeException(errorMessage))

        Assert.assertEquals(errorMessage, selectionViewModel.getAirportsLiveData().value?.message)
    }

    private fun stubGetAirportsUseCase() {
        `when`(mockGetAirport.execute(any(), any())).thenReturn(selectionViewModel.AirportsSubscriber())
    }


}
