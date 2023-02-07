package com.navdroid.apicallwithkotlinflow

import android.app.Application
import com.navdroid.apicallwithkotlinflow.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplicationClass)
            modules(appModule)
        }
    }
}
