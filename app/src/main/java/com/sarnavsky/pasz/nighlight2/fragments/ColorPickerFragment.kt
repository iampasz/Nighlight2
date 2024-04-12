package com.sarnavsky.pasz.nighlight2.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener
import com.sarnavsky.pasz.nighlight2.R
import com.sarnavsky.pasz.nighlight2.SettingsViewModel
import com.sarnavsky.pasz.nighlight2.data.db.entity.Settings
import com.sarnavsky.pasz.nighlight2.databinding.ColorPickerBinding
import com.sarnavsky.pasz.nighlight2.util.BG_COLOR_BUTTON
import com.sarnavsky.pasz.nighlight2.util.NL_COLOR_BUTTON
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ColorPickerFragment : Fragment(), ColorPickerDialogListener {

    lateinit var binding: ColorPickerBinding

    private val viewModel: SettingsViewModel by activityViewModel()

    private lateinit var mySetting: Settings

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ColorPickerBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val colorPickerDialog = ColorPickerDialog
            .newBuilder()
            .setSelectedButtonText(R.string.selected_button)
            .setDialogTitle(R.string.dialog_title)
            .setCustomButtonText(R.string.custom_button)
            .setPresetsButtonText(R.string.presets_button)
            .setColor(Color.RED)
            .create()
        colorPickerDialog.show(parentFragmentManager, "")
        colorPickerDialog.setColorPickerDialogListener(this)
        observer()
    }

    override fun onColorSelected(dialogId: Int, color: Int) {
        when (arguments?.getInt("type")) {
            BG_COLOR_BUTTON -> {
                mySetting.backgroundColor = color
            }

            NL_COLOR_BUTTON -> {
                mySetting.nightlightColor = color
            }
        }
        viewModel.updateSettings(mySetting)
    }

    override fun onDialogDismissed(dialogId: Int) {
    }


    private fun observer() {

        viewModel.getSettings()

        viewModel.settingsLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                viewModel.insertItem()
            } else {
                mySetting = it
            }
        }
    }
}