package com.sarnavsky.pasz.nighlight2

import android.app.Application
import com.sarnavsky.pasz.nighlight2.di.appModule
import com.sarnavsky.pasz.nighlight2.di.viewModelModule
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
            modules(viewModelModule)
        }
    }
}