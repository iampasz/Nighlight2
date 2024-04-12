package com.sarnavsky.pasz.nighlight2.di

import androidx.room.Room
import com.sarnavsky.pasz.nighlight2.data.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val dbModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "app_data_base.db"
        ).fallbackToDestructiveMigration().build()
    }


   single { get<AppDatabase>().settingsDao() }
   single { get<AppDatabase>().timerDao() }



}


