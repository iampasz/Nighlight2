package com.sarnavsky.pasz.nighlight2

import android.app.Application
import com.sarnavsky.pasz.nighlight2.di.dbModule
import com.sarnavsky.pasz.nighlight2.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(dbModule)
            modules(viewModelModule)

        }
    }
}