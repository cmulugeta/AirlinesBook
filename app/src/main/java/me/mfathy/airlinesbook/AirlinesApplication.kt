package me.cmulugeta.airlinesbook

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import me.cmulugeta.airlinesbook.injection.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 17/12/2018.
 * dev.cmulugeta@gmail.com
 * Base Application class >> used to inject dagger modules to use DI.
 */
class AirlinesApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        setupDagger()
    }

    private fun setupDagger() {
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
