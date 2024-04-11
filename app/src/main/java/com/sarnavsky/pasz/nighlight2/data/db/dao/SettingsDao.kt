package com.sarnavsky.pasz.nighlight2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sarnavsky.pasz.nighlight2.data.db.entity.Settings

@Dao
interface SettingsDao {
    @Insert
    suspend fun insert(settings: Settings)

    @Update
    suspend fun update(settings: Settings)

    @Query("SELECT * FROM settings WHERE id = 1")
    suspend fun getSettings(): Settings?

    @Query("SELECT * FROM settings WHERE id = 1")
    fun getSettingsLiveData(): LiveData<Settings>

    @Update(entity = Settings::class)
     suspend fun update(obj: TimerUpdate)

    @Entity
     data class TimerUpdate (
        @ColumnInfo("id") val id: Int,
        @ColumnInfo("nightlightColor") val nightlightColor: Int
    )
}