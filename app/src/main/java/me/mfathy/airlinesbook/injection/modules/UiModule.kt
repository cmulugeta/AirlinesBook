package me.cmulugeta.airlinesbook.injection.modules

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.cmulugeta.airlinesbook.domain.executor.ExecutionThread
import me.cmulugeta.airlinesbook.domain.executor.SubscribeThread
import me.cmulugeta.airlinesbook.domain.executor.scheduler.IOThread
import me.cmulugeta.airlinesbook.domain.executor.scheduler.UIThread
import me.cmulugeta.airlinesbook.ui.details.FlightDetailsActivity
import me.cmulugeta.airlinesbook.ui.search.SearchFlightsActivity
import me.cmulugeta.airlinesbook.ui.select.SelectionActivity

/**
 * Dagger module to provide UI and activities dependencies.
 */
@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(executionThread: UIThread): ExecutionThread

    @Binds
    abstract fun bindSubscribeThread(executionThread: IOThread): SubscribeThread

    @ContributesAndroidInjector
    abstract fun contributesSearchFlightsActivity(): SearchFlightsActivity

    @ContributesAndroidInjector
    abstract fun contributesSelectionActivity(): SelectionActivity

    @ContributesAndroidInjector
    abstract fun contributesFlightDetailsActivity(): FlightDetailsActivity
}