package com.sarnavsky.pasz.nighlight2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.sarnavsky.pasz.nighlight2.R
import com.sarnavsky.pasz.nighlight2.databinding.TimerFragmentBinding

class TimerFragment : Fragment() {

    private lateinit var binding: TimerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TimerFragmentBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemsHours = arrayOf(0, 1, 2, 3, 4, 6)
        val adapterHours = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, itemsHours)
        adapterHours.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerHours.adapter = adapterHours
        binding.spinnerHours.setSelection(0)
        val itemsMinutes = arrayOf(0, 1, 5, 10, 20, 30, 40, 50)
        val adapterMinutes =
            ArrayAdapter(requireContext(), R.layout.simple_spinner_item, itemsMinutes)
        adapterMinutes.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerMinutes.adapter = adapterMinutes
        binding.spinnerMinutes.setSelection(0)
        binding.noButton.setOnClickListener { removeThisFragment() }
        binding.yesButton.setOnClickListener {
            val mainFragment =
                parentFragmentManager.findFragmentByTag("main_fragment") as MainFragment?
            if (mainFragment != null) {
                mainFragment.startTimer(
                    binding.spinnerHours.selectedItem as Int,
                    binding.spinnerMinutes.selectedItem as Int
                )
                removeThisFragment()
            }
        }
    }

    private fun removeThisFragment() {
        val fm = parentFragmentManager
        fm.beginTransaction().remove(this@TimerFragment).commit()
    }
}