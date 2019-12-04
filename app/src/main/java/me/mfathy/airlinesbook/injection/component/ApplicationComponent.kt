package me.cmulugeta.airlinesbook.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import me.cmulugeta.airlinesbook.AirlinesApplication
import me.cmulugeta.airlinesbook.di.modules.*
import me.cmulugeta.airlinesbook.injection.modules.ApplicationModule
import me.cmulugeta.airlinesbook.injection.modules.UiModule
import me.cmulugeta.airlinesbook.injection.modules.ViewModelsModule
import javax.inject.Singleton

/**
 * Dagger application components
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ApplicationModule::class,
    UiModule::class,
    ViewModelsModule::class,
    AuthModule::class,
    AirportsModule::class,
    SchedulesModule::class,
    CacheModule::class,
    MemoryModule::class,
    RemoteModule::class])
interface ApplicationComponent : AndroidInjector<AirlinesApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

}