package com.sarnavsky.pasz.nighlight2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.sarnavsky.pasz.nighlight2.R
import com.sarnavsky.pasz.nighlight2.SettingsViewModel
import com.sarnavsky.pasz.nighlight2.data.db.entity.Timer
import com.sarnavsky.pasz.nighlight2.databinding.TimerFragmentBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class TimerFragment : Fragment() {

    private lateinit var binding: TimerFragmentBinding
    private  var myTimer: Timer? = null
    private val viewModel: SettingsViewModel by activityViewModel()

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

        observer()
        initListener()
        initAdapter()
    }

    private fun removeThisFragment() {
        val fm = parentFragmentManager
        fm.beginTransaction().remove(this@TimerFragment).commit()
    }

    private fun observer() {
        viewModel.getTimer()
        viewModel.timerLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                viewModel.insertTimer()
            } else {
                myTimer = it
            }

            binding.spinnerHours.setSelection(it.lastTimerHour)
            binding.spinnerMinutes.setSelection(it.lastTimerMinute)
        }
    }

    private fun convertHoursAndMinutesToMilliseconds(hours: Int, minutes: Int): Int {
        val totalMinutes = hours * 60 + minutes
        return totalMinutes * 60 * 1000
    }

    private fun initListener() {
        binding.noButton.setOnClickListener { removeThisFragment() }
        binding.setButton.setOnClickListener {
            val timerMilliseconds = convertHoursAndMinutesToMilliseconds(
                binding.spinnerHours.selectedItem as Int,
                binding.spinnerMinutes.selectedItem as Int
            )

            if (timerMilliseconds > 0) {
                myTimer?.timerDuration = timerMilliseconds
                myTimer?.timerStatus = true
            } else {
                myTimer?.timerDuration = 0
                myTimer?.timerStatus = false
            }

            myTimer?.lastTimerHour = binding.spinnerHours.selectedItemPosition
            myTimer?.lastTimerMinute = binding.spinnerMinutes.selectedItemPosition

            myTimer?.let {
                viewModel.updateTimer(it)
            }


            removeThisFragment()
        }
    }

    private fun initAdapter() {

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
    }

}