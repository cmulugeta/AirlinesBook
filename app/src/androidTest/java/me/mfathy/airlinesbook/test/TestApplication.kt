package me.cmulugeta.airlinesbook.test

import android.app.Activity
import android.app.Application
import androidx.test.InstrumentationRegistry
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import me.cmulugeta.airlinesbook.injection.DaggerTestApplicationComponent
import me.cmulugeta.airlinesbook.injection.TestApplicationComponent
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 15/12/2018.
 * dev.cmulugeta@gmail.com
 * Testing application class used to provide context and dagger modules for tests.
 */
class TestApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>
    private lateinit var appComponent: TestApplicationComponent

    companion object {
        fun appComponent(): TestApplicationComponent {
            return (InstrumentationRegistry.getTargetContext().applicationContext
                    as TestApplication).appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerTestApplicationComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}