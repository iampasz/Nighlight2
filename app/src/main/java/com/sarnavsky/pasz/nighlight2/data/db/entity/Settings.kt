package com.sarnavsky.pasz.nighlight2.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings(
    @PrimaryKey
    val id: Int = 1,
    var currentNightlight: Int,
    var backgroundColor: Int,
    var nightlightColor: Int,
    var timerStatus: Boolean,
    var timerDuration: Int,
    var lastTimerHour: Int,
    var lastTimerMinute: Int,
    var animationType: Int,
    var animationStatus: Boolean

)
