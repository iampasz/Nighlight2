//package com.sarnavsky.pasz.nighlight2
//
//import android.media.MediaPlayer
//
//class classMediaPlayerController {
//
//    private var oldLinkId = 0
//    private var mediaPlayer: MediaPlayer? = null
//
//    fun playSound(linkId: Int) {
//        if (mediaPlayer != null) {
//            mediaPlayer?.pause()
//            mediaPlayer?.reset()
//            mediaPlayer = null
//        }
//        if (oldLinkId == linkId) {
//            oldLinkId = 0
//        } else {
////            mediaPlayer = MediaPlayer.create(
////                this,
////                linkId
//           // )
//            mediaPlayer?.isLooping = true
//            mediaPlayer?.start()
//            oldLinkId = linkId
//        }
//    }
//
//    fun pauseMediaPlayer() {
//        if (mediaPlayer != null) {
//            mediaPlayer?.pause()
//        }
//    }
//
//    fun resumeMediaPlayer() {
//        if (mediaPlayer != null) {
//            mediaPlayer?.start()
//        }
//    }
//
//    fun finishMedia() {
//        if (mediaPlayer != null) {
//            mediaPlayer?.pause()
//            mediaPlayer?.reset()
//            mediaPlayer = null
//        }
//    }
//
//    fun stopSound() {
//        oldLinkId = 0
//        if (mediaPlayer != null) {
//            mediaPlayer?.pause()
//            mediaPlayer?.reset()
//            mediaPlayer = null
//        }
//    }
//}