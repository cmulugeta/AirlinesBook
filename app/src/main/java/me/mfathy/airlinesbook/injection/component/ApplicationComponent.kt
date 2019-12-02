package me.cmulugeta.airlinesbook.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import me.cmulugeta.airlinesbook.AirlinesApplication
import me.cmulugeta.airlinesbook.di.CacheModule
import me.cmulugeta.airlinesbook.di.MemoryModule
import me.cmulugeta.airlinesbook.di.RemoteModule
import me.cmulugeta.airlinesbook.di.RetrofitModule
import me.cmulugeta.airlinesbook.injection.modules.*
import javax.inject.Singleton

/**
 * Dagger application components
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ApplicationModule::class,
    UiModule::class,
    ViewModelsModule::class,
    DataModule::class,
    CacheModule::class,
    MemoryModule::class,
    RetrofitModule::class])
interface ApplicationComponent : AndroidInjector<AirlinesApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

}