package com.sarnavsky.pasz.nighlight2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sarnavsky.pasz.nighlight2.data.db.dao.SettingsDao
import com.sarnavsky.pasz.nighlight2.data.db.dao.TimerDao
import com.sarnavsky.pasz.nighlight2.data.db.entity.Settings
import com.sarnavsky.pasz.nighlight2.data.db.entity.Timer

@Database(
    entities =
    [Settings::class, Timer::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
    abstract fun timerDao(): TimerDao
}

