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
import com.sarnavsky.pasz.nighlight2.databinding.ColorPickerBinding

class ColorPickerFragment : Fragment(), ColorPickerDialogListener {

    lateinit var binding: ColorPickerBinding

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
//        val main_fragment =
//            parentFragmentManager.findFragmentByTag("main_fragment") as MainFragment?
//        if (main_fragment != null) {
//            // mainBg =  main_fragment.mainBg;
//            //underImg =  main_fragment.underImg;
//        }
    }

    override fun onColorSelected(dialogId: Int, color: Int) {
//        if (dialogId == 0) { // We got result from the dialog that is shown when clicking on the icon in the action bar.
////            if (arguments!!.getInt("id") == 0) {
////                underImg.setColorFilter(color)
////            } else {
////                mainBg.setBackgroundColor(color)
////            }
//        }
    }

    override fun onDialogDismissed(dialogId: Int) {
    }

}