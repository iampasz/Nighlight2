package com.sarnavsky.pasz.nighlight2.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sarnavsky.pasz.nighlight2.MediaPlayerViewModel
import com.sarnavsky.pasz.nighlight2.adapters.MusicListAdapter
import com.sarnavsky.pasz.nighlight2.databinding.ListFragmentBinding
import com.sarnavsky.pasz.nighlight2.util.AudioHelper
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MusicListFragment : Fragment() {

    private val mediaPlayerViewModel: MediaPlayerViewModel by activityViewModel()

    private lateinit var binding: ListFragmentBinding
    private val musicListAdapter = MusicListAdapter {

        if (mediaPlayerViewModel.isPlaying()) {
            mediaPlayerViewModel.stopMediaPlayer()
        } else {
            mediaPlayerViewModel.startMediaPlayer(requireContext(), it.linkId)
        }

        mediaPlayerViewModel.currentAudioItem.value = it.audioName
    }

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

        binding.rv.adapter = musicListAdapter

        val audioList = AudioHelper.getAudioList()
        musicListAdapter.list.submitList(audioList)

        binding.close.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .remove(this@MusicListFragment)
                .commit()
        }

       if(mediaPlayerViewModel.isPlaying()) {
           val foundAudio = audioList.find { it.audioName == mediaPlayerViewModel.currentAudioItem.value }

           foundAudio?.let {
               foundAudio.status = true
               musicListAdapter.list.submitList(audioList)
               Log.i("FEUFNEFE", "true")
           }

           Log.i("FEUFNEFE", "123")
       }

//        mediaPlayerViewModel.currentAudioItem.observe(viewLifecycleOwner){
//            Log.i("FEUFNEFE", it)
//            if(it!=""){
//                mediaPlayerViewModel.currentAudioItem.value = ""
//            }
//        }
    }
}