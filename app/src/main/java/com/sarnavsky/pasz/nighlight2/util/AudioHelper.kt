package com.sarnavsky.pasz.nighlight2.util

import com.sarnavsky.pasz.nighlight2.R
import com.sarnavsky.pasz.nighlight2.objects.AudioItem

class AudioHelper {
    companion object {

        fun getAudioList(): ArrayList<AudioItem> {

            val arrayList = ArrayList<AudioItem>()

            val myAudio0 = AudioItem()
            val myAudio1 = AudioItem()
            val myAudio2 = AudioItem()
            val myAudio3 = AudioItem()

            myAudio0.linkId = R.raw.detskaya
            myAudio0.audioName = "Dream flight"
            myAudio0.audioAuth = "Lullaby"
            myAudio1.linkId = R.raw.eho
            myAudio1.audioName = "Cristal Rain"
            myAudio1.audioAuth = "Xu King-Yuan"
            myAudio2.linkId = R.raw.nostalgi
            myAudio2.audioName = "The Scents of Nostalgia"
            myAudio2.audioAuth = "Xu King-Yuan"
            myAudio3.linkId = R.raw.sound
            myAudio3.audioName = "Shout To The Lord"
            myAudio3.audioAuth = "Judson Mancebo"
            arrayList.add(myAudio0)
            arrayList.add(myAudio1)
            arrayList.add(myAudio2)
            arrayList.add(myAudio3)

            return arrayList
        }
    }
}