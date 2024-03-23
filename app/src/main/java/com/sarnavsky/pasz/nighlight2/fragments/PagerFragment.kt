package com.sarnavsky.pasz.nighlight2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.sarnavsky.pasz.nighlight2.databinding.NightlightFragmentBinding
import java.util.Random

class PagerFragment : Fragment() {

    lateinit var binding: NightlightFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NightlightFragmentBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //binding.underImg.setImageResource(myNightlighter.getDownImg())
        //binding.upImg.setImageResource(myNightlighter.getUpImg())

        val techniques = ArrayList<Techniques>()
        techniques.add(Techniques.Bounce)
        techniques.add(Techniques.BounceIn)
        techniques.add(Techniques.FadeIn)
        techniques.add(Techniques.DropOut)
        techniques.add(Techniques.Shake)
        techniques.add(Techniques.Flash)
        techniques.add(Techniques.SlideInLeft)
        techniques.add(Techniques.Swing)
        techniques.add(Techniques.FlipInY)
        binding.upImg.setOnClickListener {
            val random = Random()
            val i = random.nextInt(techniques.size)
            YoYo.with(techniques[i])
                .duration(700)
                .playOn(binding.upImg)
            YoYo.with(techniques[i])
                .duration(700)
                .playOn(binding.underImg)
        }
    }
}