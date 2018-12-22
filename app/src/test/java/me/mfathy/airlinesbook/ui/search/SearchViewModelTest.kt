package me.cmulugeta.airlinesbook.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import io.reactivex.subscribers.DisposableSubscriber
import me.cmulugeta.airlinesbook.data.model.AccessTokenEntity
import me.cmulugeta.airlinesbook.data.model.ScheduleEntity
import me.cmulugeta.airlinesbook.domain.interactor.schedules.GetFlightSchedules
import me.cmulugeta.airlinesbook.domain.interactor.token.GetAccessToken
import me.cmulugeta.airlinesbook.factory.AirportFactory
import me.cmulugeta.airlinesbook.factory.DataFactory
import me.cmulugeta.airlinesbook.ui.state.ResourceState
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

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
    private val mockGetFlightSchedules: GetFlightSchedules = mock()
    private val mockGetAccessToken: GetAccessToken = mock()
    var searchViewModel = SearchViewModel(mockGetFlightSchedules, mockGetAccessToken)

    @Captor
    val captor = argumentCaptor<DisposableSubscriber<List<ScheduleEntity>>>()

    @Captor
    val captorAccessToken = argumentCaptor<DisposableObserver<AccessTokenEntity>>()

    @Test
    fun testFetchFlightSchedulesExecutesUseCase() {
        searchViewModel.fetchFlightSchedules("CAI", "RUH", "", 1, 1)

        verify(mockGetFlightSchedules, times(1)).execute(any(), any())
    }

    @Test
    fun testFetchFlightSchedulesReturnsSuccess() {
        val schedules = listOf(AirportFactory.makeScheduleEntity())

        searchViewModel.fetchFlightSchedules("CAI", "RUH", "", 1, 1)

        verify(mockGetFlightSchedules).execute(captor.capture(), any())
        captor.firstValue.onNext(schedules)

        Assert.assertEquals(ResourceState.SUCCESS, searchViewModel.getSchedulesLiveData().value?.status)
    }

    @Test
    fun testFetchFlightSchedulesReturnsData() {
        val schedules = listOf(AirportFactory.makeScheduleEntity())

        searchViewModel.fetchFlightSchedules("CAI", "RUH", "", 1, 1)

        verify(mockGetFlightSchedules).execute(captor.capture(), any())
        captor.firstValue.onNext(schedules)

        Assert.assertEquals(schedules, searchViewModel.getSchedulesLiveData().value?.data)
    }

    @Test
    fun testFetchFlightSchedulesReturnsError() {

        searchViewModel.fetchFlightSchedules("CAI", "RUH", "", 1, 1)

        verify(mockGetFlightSchedules).execute(captor.capture(), any())
        captor.firstValue.onError(RuntimeException())

        Assert.assertEquals(ResourceState.ERROR, searchViewModel.getSchedulesLiveData().value?.status)
    }

    @Test
    fun testFetchFlightSchedulesReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        searchViewModel.fetchFlightSchedules("CAI", "RUH", "", 1, 1)

        verify(mockGetFlightSchedules).execute(captor.capture(), any())
        captor.firstValue.onError(RuntimeException(errorMessage))

        Assert.assertEquals(errorMessage, searchViewModel.getSchedulesLiveData().value?.message)
    }

    @Test
    fun testFetchAccessTokenExecutesUseCase() {
        searchViewModel.authenticateApp(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())

        verify(mockGetAccessToken, times(1)).execute(any(), any())
    }

    @Test
    fun testFetchAccessTokenReturnsSuccess() {
        val token = AirportFactory.makeAccessTokenEntity()

        searchViewModel.authenticateApp(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())

        verify(mockGetAccessToken).execute(captorAccessToken.capture(), any())
        captorAccessToken.firstValue.onNext(token)

        Assert.assertEquals(ResourceState.SUCCESS, searchViewModel.getAccessTokenLiveData().value?.status)
    }

    @Test
    fun testFetchAccessTokenReturnsData() {
        val token = AirportFactory.makeAccessTokenEntity()

        searchViewModel.authenticateApp(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())

        verify(mockGetAccessToken).execute(captorAccessToken.capture(), any())
        captorAccessToken.firstValue.onNext(token)

        Assert.assertEquals(token, searchViewModel.getAccessTokenLiveData().value?.data)
    }

    @Test
    fun testFetchAccessTokenReturnsError() {

        searchViewModel.authenticateApp(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())

        verify(mockGetAccessToken).execute(captorAccessToken.capture(), any())
        captorAccessToken.firstValue.onError(RuntimeException())

        Assert.assertEquals(ResourceState.ERROR, searchViewModel.getAccessTokenLiveData().value?.status)
    }

    @Test
    fun testFetchAccessTokenReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        searchViewModel.authenticateApp(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())

        verify(mockGetAccessToken).execute(captorAccessToken.capture(), any())
        captorAccessToken.firstValue.onError(RuntimeException(errorMessage))

        Assert.assertEquals(errorMessage, searchViewModel.getAccessTokenLiveData().value?.message)
    }
}