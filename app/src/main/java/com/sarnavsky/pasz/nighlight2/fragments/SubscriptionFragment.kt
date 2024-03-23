package com.sarnavsky.pasz.nighlight2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sarnavsky.pasz.nighlight2.databinding.SubscriptionBinding

class SubscriptionFragment : Fragment() {

    lateinit var binding: SubscriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SubscriptionBinding
            .inflate(inflater, container, false)
        return binding.root
    }
}