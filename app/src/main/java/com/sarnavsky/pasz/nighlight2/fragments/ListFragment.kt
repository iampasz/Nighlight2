package com.sarnavsky.pasz.nighlight2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sarnavsky.pasz.nighlight2.objects.MyAudio
import com.sarnavsky.pasz.nighlight2.R
import com.sarnavsky.pasz.nighlight2.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    private lateinit var binding: ListFragmentBinding

    //private var currentMusicPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding
            .inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayList = ArrayList<MyAudio>()
        val myAudio0 = MyAudio()
        val myAudio1 = MyAudio()
        val myAudio2 = MyAudio()
        val myAudio3 = MyAudio()

        myAudio0.linkId = R.raw.detskaya
        myAudio0.name = "Dream flight"
        myAudio0.auth = "Lullaby"
        myAudio1.linkId = R.raw.eho
        myAudio1.name = "Cristal Rain"
        myAudio1.auth = "Xu King-Yuan"
        myAudio2.linkId = R.raw.nostalgi
        myAudio2.name = "The Scents of Nostalgia"
        myAudio2.auth = "Xu King-Yuan"
        myAudio3.linkId = R.raw.sound
        myAudio3.name = "Shout To The Lord"
        myAudio3.auth = "Judson Mancebo"
        arrayList.add(myAudio0)
        arrayList.add(myAudio1)
        arrayList.add(myAudio2)
        arrayList.add(myAudio3)
       // (activity as MainActivity?)!!.stopSound()
//        val  myListAdapter =
//            ListMusicAdapter({ position, playStatus ->
//                (activity as MainActivity?)?.playSound(arrayList[position].linkId)
//                currentMusicPosition = if (playStatus) {
//                    position
//                } else {
//                    -1
//                }
//            }, arrayList)

        binding.rv.layoutManager = LinearLayoutManager(context)
        //binding.rv.adapter = myListAdapter
        binding.close.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this@ListFragment).commit()
        }
    }
}