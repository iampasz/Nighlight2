package com.sarnavsky.pasz.nighlight2.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timer")
data class Timer (
    @PrimaryKey
    val id: Int = 1,
    var timerStatus: Boolean,
    var timerDuration: Int,
    var lastTimerHour: Int,
    var lastTimerMinute: Int
)
