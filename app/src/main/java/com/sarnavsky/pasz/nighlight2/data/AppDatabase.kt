package com.kokooko.easylight.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sarnavsky.pasz.nighlight2.data.db.dao.SettingsDao
import com.sarnavsky.pasz.nighlight2.data.db.entity.Settings

@Database(
    entities =
    [Settings::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao

}

