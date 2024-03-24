package com.sarnavsky.pasz.nighlight2.objects

data class AudioItem(
    var audioName: String = "",
    var audioAuth: String = "",
    var link: String = "",
    var status: Boolean = false,
    var linkId: Int = 0
)