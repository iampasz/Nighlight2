package com.sarnavsky.pasz.nighlight2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sarnavsky.pasz.nighlight2.data.db.entity.Timer

@Dao
interface TimerDao {
    @Insert
    suspend fun insert(timer: Timer)

    @Update
    suspend fun update(timer: Timer)

    @Query("SELECT * FROM timer WHERE id = 1")
    suspend fun getTimer(): Timer?

    @Query("SELECT * FROM timer WHERE id = 1")
    fun getTimerLiveData(): LiveData<Timer>

}