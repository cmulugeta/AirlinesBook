package me.cmulugeta.airlinesbook.domain.interactor.airports

import io.reactivex.Completable
import me.cmulugeta.airlinesbook.ImmediateSchedulerRuleUnitTests
import me.cmulugeta.airlinesbook.data.repository.AirportsRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
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

    @JvmField
    @Rule
    val immediateSchedulerRule = ImmediateSchedulerRuleUnitTests()

    private lateinit var mClearAirports: ClearAirports

    @Mock
    lateinit var mockDataRepository: AirportsRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mClearAirports = ClearAirports(mockDataRepository)
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
        `when`(mockDataRepository.clearAirports()).thenReturn(completable)
    }
}
