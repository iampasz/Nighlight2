package com.sarnavsky.pasz.nighlight2

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarnavsky.pasz.nighlight2.data.db.dao.SettingsDao
import com.sarnavsky.pasz.nighlight2.data.db.entity.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(private val settingsDao: SettingsDao) : ViewModel() {

    val settings = MutableLiveData<Settings>()
    val settingsLiveData: LiveData<Settings> = settingsDao.getSettingsLiveData()


    fun insertItem() {
        viewModelScope.launch {
            val newItem = Settings(
                currentNightlight = 0,
                backgroundColor = Color.BLACK,
                nightlightColor = Color.BLACK,
                timerStatus = false,
                timerDuration = 999,
                lastTimerHour = 0,
                lastTimerMinute = 0
            )
            settingsDao.insert(newItem)
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


}