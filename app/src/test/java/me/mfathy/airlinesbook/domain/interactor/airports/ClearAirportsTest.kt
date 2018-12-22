package me.cmulugeta.airlinesbook.domain.interactor.airports

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import me.cmulugeta.airlinesbook.data.repository.AirportsRepository
import me.cmulugeta.airlinesbook.domain.executor.ExecutionThread
import me.cmulugeta.airlinesbook.domain.executor.SubscribeThread
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * Unit test for ClearAirports use case.
 */
@RunWith(MockitoJUnitRunner::class)
class ClearAirportsTest {

    private lateinit var mClearAirports: ClearAirports

    @Mock
    lateinit var mockDataRepository: AirportsRepository
    @Mock
    lateinit var mockExecutionThread: ExecutionThread

    @Mock
    lateinit var mockSubscribeThread: SubscribeThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mClearAirports = ClearAirports(mockDataRepository, mockSubscribeThread, mockExecutionThread)
    }

    @Test
    fun testClearAirportsCompletes() {
        stubDataRepositoryClearAirports(Completable.complete())

        val testObserver = mClearAirports.buildUseCaseCompletable().test()
        testObserver.assertComplete()
    }

    @Test
    fun testGetAirportsCallsRepository() {
        stubDataRepositoryClearAirports(Completable.complete())

        mClearAirports.buildUseCaseCompletable().test()

        verify(mockDataRepository).clearAirports()
    }

    private fun stubDataRepositoryClearAirports(completable: Completable) {
        whenever(mockDataRepository.clearAirports()).thenReturn(completable)
    }
}