package com.sarnavsky.pasz.nighlight2

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MediaPlayerViewModel : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying: Boolean = false


    var currentAudioItem = MutableLiveData<String>()

    fun initializeMediaPlayer() {
        mediaPlayer = MediaPlayer()
    }


    fun startMediaPlayer(context: Context, resId: Int) {
        mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
        isPlaying = true
    }

//    fun pauseMediaPlayer() {
//        mediaPlayer?.pause()
//        isPlaying = false
//    }

    fun stopMediaPlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying = false
    }

    fun isPlaying(): Boolean {
        return isPlaying
    }

    override fun onCleared() {
        super.onCleared()
        stopMediaPlayer()
    }
}