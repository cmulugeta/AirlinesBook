package me.cmulugeta.airlinesbook.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import me.cmulugeta.airlinesbook.injection.ViewModelFactory
import me.cmulugeta.airlinesbook.features.details.FlightDetailsViewModel
import me.cmulugeta.airlinesbook.features.search.SearchViewModel
import me.cmulugeta.airlinesbook.features.select.SelectionViewModel
import kotlin.reflect.KClass

/**
 * Dagger module to provide ViewModel dependencies.
 */
@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SelectionViewModel::class)
    abstract fun bindSelectionViewModel(viewModel: SelectionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FlightDetailsViewModel::class)
    abstract fun bindFlightDetailsViewModel(viewModel: FlightDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
