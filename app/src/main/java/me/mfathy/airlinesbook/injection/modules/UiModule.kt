package me.cmulugeta.airlinesbook.injection.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.cmulugeta.airlinesbook.ui.details.FlightDetailsActivity
import me.cmulugeta.airlinesbook.ui.search.SearchFlightsActivity
import me.cmulugeta.airlinesbook.ui.select.SelectionActivity

/**
 * Dagger module to provide UI and activities dependencies.
 */
@Module
abstract class UiModule {


    @ContributesAndroidInjector
    abstract fun contributesSearchFlightsActivity(): SearchFlightsActivity

    @ContributesAndroidInjector
    abstract fun contributesSelectionActivity(): SelectionActivity

    @ContributesAndroidInjector
    abstract fun contributesFlightDetailsActivity(): FlightDetailsActivity
}