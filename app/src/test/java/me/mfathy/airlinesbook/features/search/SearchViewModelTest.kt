package me.cmulugeta.airlinesbook.features.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.observers.DisposableObserver
import io.reactivex.subscribers.DisposableSubscriber
import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import me.cmulugeta.airlinesbook.data.model.ScheduleEntity
import me.cmulugeta.airlinesbook.domain.interactor.schedules.GetFlightSchedules
import me.cmulugeta.airlinesbook.domain.interactor.token.GetAccessToken
import me.cmulugeta.airlinesbook.factory.AirportFactory
import me.cmulugeta.airlinesbook.factory.DataFactory
import me.cmulugeta.airlinesbook.features.state.ResourceState
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
 * Unit tests for SearchViewModel
 */
@RunWith(JUnit4::class)
class SearchViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val immediateSchedulerRule = me.cmulugeta.test.tools.ImmediateSchedulerRuleUnitTests()

    private val mockGetFlightSchedules = mock(GetFlightSchedules::class.java)
    private val mockGetAccessToken = mock(GetAccessToken::class.java)
    private var searchViewModel = SearchViewModel(mockGetFlightSchedules, mockGetAccessToken)

    @Captor
    val airportListCaptor = me.cmulugeta.test.tools.argumentCaptor<DisposableSubscriber<List<ScheduleEntity>>>()

    @Captor
    val captorAccessToken = me.cmulugeta.test.tools.argumentCaptor<DisposableObserver<AccessTokenEntity>>()

    @Test
    fun testFetchFlightSchedulesExecutesUseCase() {

        stubGetFlightSchedulesUseCase()

        searchViewModel.fetchFlightSchedules("CAI", "RUH", "", 1, 1)

        verify(mockGetFlightSchedules, times(1)).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(airportListCaptor))
    }

    @Test
    fun testFetchFlightSchedulesReturnsSuccess() {
        val schedules = listOf(AirportFactory.makeScheduleEntity())

        stubGetFlightSchedulesUseCase()

        searchViewModel.fetchFlightSchedules("CAI", "RUH", "", 1, 1)

        verify(mockGetFlightSchedules).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(airportListCaptor))

        airportListCaptor.value.onNext(schedules)

        Assert.assertEquals(ResourceState.SUCCESS, searchViewModel.getSchedulesLiveData().value?.status)
    }

    @Test
    fun testFetchFlightSchedulesReturnsData() {
        val schedules = listOf(AirportFactory.makeScheduleEntity())

        stubGetFlightSchedulesUseCase()

        searchViewModel.fetchFlightSchedules("CAI", "RUH", "", 1, 1)

        verify(mockGetFlightSchedules).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(airportListCaptor))

        airportListCaptor.value.onNext(schedules)

        Assert.assertEquals(schedules, searchViewModel.getSchedulesLiveData().value?.data)
    }

    @Test
    fun testFetchFlightSchedulesReturnsError() {
        stubGetFlightSchedulesUseCase()

        searchViewModel.fetchFlightSchedules("CAI", "RUH", "", 1, 1)

        verify(mockGetFlightSchedules).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(airportListCaptor))

        airportListCaptor.value.onError(RuntimeException())

        Assert.assertEquals(ResourceState.ERROR, searchViewModel.getSchedulesLiveData().value?.status)
    }

    @Test
    fun testFetchFlightSchedulesReturnsMessageForError() {
        stubGetFlightSchedulesUseCase()

        val errorMessage = DataFactory.randomString()
        searchViewModel.fetchFlightSchedules("CAI", "RUH", "", 1, 1)

        verify(mockGetFlightSchedules).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(airportListCaptor))

        airportListCaptor.value.onError(RuntimeException(errorMessage))

        Assert.assertEquals(errorMessage, searchViewModel.getSchedulesLiveData().value?.message)
    }

    @Test
    fun testFetchAccessTokenExecutesUseCase() {
        stubGetAccessTokenUseCase()

        searchViewModel.authenticateApp(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())

        verify(mockGetAccessToken, times(1)).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.any())
    }

    @Test
    fun testFetchAccessTokenReturnsSuccess() {
        stubGetAccessTokenUseCase()

        val token = AirportFactory.makeAccessTokenEntity()

        searchViewModel.authenticateApp(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())

        verify(mockGetAccessToken).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(captorAccessToken))


        captorAccessToken.value.onNext(token)

        Assert.assertEquals(ResourceState.SUCCESS, searchViewModel.getAccessTokenLiveData().value?.status)
    }

    @Test
    fun testFetchAccessTokenReturnsData() {

        stubGetAccessTokenUseCase()

        val token = AirportFactory.makeAccessTokenEntity()

        searchViewModel.authenticateApp(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())

        verify(mockGetAccessToken).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(captorAccessToken))

        captorAccessToken.value.onNext(token)

        Assert.assertEquals(token, searchViewModel.getAccessTokenLiveData().value?.data)
    }

    @Test
    fun testFetchAccessTokenReturnsError() {
        stubGetAccessTokenUseCase()

        searchViewModel.authenticateApp(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())

        verify(mockGetAccessToken).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(captorAccessToken))


        captorAccessToken.value.onError(RuntimeException())

        Assert.assertEquals(ResourceState.ERROR, searchViewModel.getAccessTokenLiveData().value?.status)
    }

    @Test
    fun testFetchAccessTokenReturnsMessageForError() {
        stubGetAccessTokenUseCase()

        val errorMessage = DataFactory.randomString()
        searchViewModel.authenticateApp(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())

        verify(mockGetAccessToken).execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.capture(captorAccessToken))

        captorAccessToken.value.onError(RuntimeException(errorMessage))

        Assert.assertEquals(errorMessage, searchViewModel.getAccessTokenLiveData().value?.message)
    }

    private fun stubGetFlightSchedulesUseCase() {
        `when`(mockGetFlightSchedules.execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.any())).thenReturn(searchViewModel.FlightSchedulesSubscriber())
    }

    private fun stubGetAccessTokenUseCase() {
        `when`(mockGetAccessToken.execute(me.cmulugeta.test.tools.any(), me.cmulugeta.test.tools.any())).thenReturn(searchViewModel.AccessTokenSubscriber())
    }
}
