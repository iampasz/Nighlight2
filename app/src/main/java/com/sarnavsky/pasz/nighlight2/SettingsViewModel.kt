package com.sarnavsky.pasz.nighlight2

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarnavsky.pasz.nighlight2.data.db.dao.SettingsDao
import com.sarnavsky.pasz.nighlight2.data.db.dao.TimerDao
import com.sarnavsky.pasz.nighlight2.data.db.entity.Settings
import com.sarnavsky.pasz.nighlight2.data.db.entity.Timer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(
    private val settingsDao: SettingsDao,
    private val timerDao: TimerDao) : ViewModel() {

    val settings = MutableLiveData<Settings>()
    val timer = MutableLiveData<Timer>()
    val settingsLiveData: LiveData<Settings> = settingsDao.getSettingsLiveData()
    val timerLiveData: LiveData<Timer> = timerDao.getTimerLiveData()


    fun insertItem() {
        viewModelScope.launch {
            val newItem = Settings(
                currentNightlight = 0,
                backgroundColor = Color.BLACK,
                nightlightColor = Color.BLUE,
                animationType = R.drawable.bg_flowers,
                animationStatus = false
            )
            settingsDao.insert(newItem)
        }
    }

    fun insertTimer(){
        viewModelScope.launch {
            val newTimer = Timer(
                timerStatus = false,
                timerDuration = 999,
                lastTimerHour = 0,
                lastTimerMinute = 0
            )
            timerDao.insert(newTimer)
        }
    }

    fun getTimer() {
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    timerDao.getTimer()
                }
            }.onSuccess { response ->
                response.let {
                    timer.value = it
                }
            }.onFailure {

            }
        }
    }

    fun getSettings() {
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    settingsDao.getSettings()
                }
            }.onSuccess { response ->
                response.let {
                    settings.value = it
                }
            }.onFailure {

            }
        }
    }


    fun updateSettings(param: Settings) {
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    settingsDao.update(param)
                }
            }.onSuccess { response ->
                response.let {
                }
            }.onFailure {
            }
        }
    }

    fun updateTimer(param: Timer) {
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    timerDao.update(param)
                }
            }.onSuccess { response ->
                response.let {
                }
            }.onFailure {
            }
        }
    }



}