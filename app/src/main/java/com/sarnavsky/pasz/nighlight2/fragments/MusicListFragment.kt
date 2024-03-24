package com.sarnavsky.pasz.nighlight2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sarnavsky.pasz.nighlight2.adapters.MusicListAdapter
import com.sarnavsky.pasz.nighlight2.databinding.ListFragmentBinding
import com.sarnavsky.pasz.nighlight2.util.AudioHelper

class MusicListFragment : Fragment() {

    private lateinit var binding: ListFragmentBinding
    private val musicListAdapter = MusicListAdapter {

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
    }
}